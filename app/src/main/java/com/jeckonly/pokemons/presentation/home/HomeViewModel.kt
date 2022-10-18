package com.jeckonly.pokemons.presentation.home

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeckonly.core_data.common.repo.interface_.PokemonRepo
import com.jeckonly.core_data.common.repo.interface_.UserPrefsRepo
import com.jeckonly.core_data.download.DownloadClient
import com.jeckonly.core_model.domain.ResourceState
import com.jeckonly.core_model.ui.home.HomeScreenMode
import com.jeckonly.core_model.ui.home.PokemonInfoUI
import com.jeckonly.pokemons.ui_event.home.HomeEvent
import com.jeckonly.util.LogUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val pokemonRepo: PokemonRepo,
    private val userPrefsRepo: UserPrefsRepo,
    private val downloadClient: DownloadClient
) : ViewModel() {

    /**
     * mode
     */
    private var screenMode: HomeScreenMode = HomeScreenMode.BrowseMode

    /**
     * 是否已下载数据库
     */
    val hasFinishDownloadStateFlow = userPrefsRepo.getDownloadStateFlow().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = false
    )

    /**
     * 是否正在运行下载任务
     */
    val isRunningDownloadState = downloadClient.getIsRunningDownloadStateFlow()

    /**
     * the pokemon items to show
     */
    private val _pokemonItems: MutableStateFlow<List<PokemonInfoUI>> = MutableStateFlow(emptyList())
    val pokemonItems: StateFlow<List<PokemonInfoUI>> = _pokemonItems

    /**
     * 浏览模式下当前已加载的最大页面序号，初始值为0
     */
    private val page: MutableStateFlow<Int> = MutableStateFlow(0)

    /**
     * 当前搜索的字符串（name or id），若不为空则为搜索模式
     */
    var searchText by mutableStateOf("")
        private set

    /**
     * lazyListState
     *
     * TODO 要达到[rememberLazyGridState]的同样效果需要保存到SavedStateHandler
     */
    val listState = LazyGridState()

    private var firstVisibleItemIndex = 0
    private var firstVisibleItemScrollOffset = 0

    private var needScrollToLastPosition = false

    /**
     * 构造一个对于列表元素数量的监听
     */
    private val totalItemCountStateFlow = snapshotFlow {
        // NOTE snapshotFlow本身就对MutableState发出的值进行了distinct处理
        listState.layoutInfo.totalItemsCount
    }.onEach {
        LogUtil.d("total item count: $it")
    }.stateIn (
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = 0
    )

    init {
        viewModelScope.launch {
            totalItemCountStateFlow.collect { _ ->
                // 当列表元素数量改变且需要滚动到原位置时，就滚动
                if (needScrollToLastPosition) {
                    scrollToLastPosition(firstVisibleItemIndex, firstVisibleItemScrollOffset)
                    needScrollToLastPosition = false
                } else {
                    if (screenMode is HomeScreenMode.SearchMode) {
                        // 当在搜索模式的时候，每当列表总数增改，都要去到顶部
                        scrollToLastPosition(0, 0)
                    }
                }
            }
        }
    }

    /**
     * 管理搜索执行的job
     */
    private var searchJob: Job? = null

    /**
     * 管理加载页数执行的job
     */
    private var loadPageJob: Job? = null

    /**
     * 保存列表的滚动位置
     */
    private fun saveListState() {
        firstVisibleItemIndex = listState.firstVisibleItemIndex
        firstVisibleItemScrollOffset = listState.firstVisibleItemScrollOffset
    }

    /**
     * 恢复滚动位置
     */
    private suspend fun scrollToLastPosition(firstVisibleItemIndex: Int, firstVisibleItemScrollOffset: Int) {
        listState.scrollToItem(firstVisibleItemIndex, firstVisibleItemScrollOffset)
//        listState.animateScrollToItem(firstVisibleItemIndex, firstVisibleItemScrollOffset) 需要在Composable内的协程中调用
    }

    /**
     * NOTE 只能被UI层调用
     */
    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.WantMoreItem -> {
                loadPage(page.value + 1)
            }
            is HomeEvent.ClickDownload -> {
                if (!isRunningDownloadState.value and !hasFinishDownloadStateFlow.value) {
                    downloadClient.downloadDatabase(
                        onStart = {
                            LogUtil.d("onStart")
                            // TODO 弹个通知说开始
                        },
                        onSuccess = {
                            LogUtil.d("onSuccess")
                            viewModelScope.launch {
                                userPrefsRepo.updateDownloadStateToFinish()
                            }
                            // TODO 弹个通知说成功
                        },
                        onFailure = {
                            LogUtil.d("onFailure")
                            // TODO 弹个通知说失败
                        }
                    )
                }
            }
        }
    }

    fun onSearchTextChanged(newValue: String) {
        searchText = newValue
        if (newValue.isEmpty()) {
            screenMode = HomeScreenMode.BrowseMode
            // 标志需要恢复滚动位置了
            needScrollToLastPosition = true
            loadPage(page.value)
        } else {
            // not empty
            // prevent change it often
            if (screenMode is HomeScreenMode.BrowseMode) {
                // 保存列表状态
                saveListState()
                screenMode = HomeScreenMode.SearchMode
            }
            searchResult(newValue)
        }
    }

    /**
     * 在浏览模式下，加载 <= [targetPage]页的所有页面
     */
    private fun loadPage(targetPage: Int) {
        if (screenMode is HomeScreenMode.BrowseMode) {
            searchJob?.cancel()
            val lastJobCompleted = loadPageJob?.isCompleted ?: true
            // 如果上一个loadPage任务还在继续，就不添加新任务，这种情况发生在页面滑到底部又上滑又下滑，发送了2次以上WantMoreItem事件
            if (!lastJobCompleted) return
            loadPageJob = viewModelScope.launch {
                // NOTE 防止频繁请求
                delay(100)
                pokemonRepo.getAllItemLessThanPage(targetPage)
                    .collect { resourceState: ResourceState<List<PokemonInfoUI>> ->
                        when (resourceState) {
                            is ResourceState.Error -> {

                            }
                            is ResourceState.Loading -> {

                            }
                            is ResourceState.Success -> {
                                // 只有成功的情况才增加页数啊
                                page.update {
                                    targetPage
                                }
                                if (screenMode is HomeScreenMode.BrowseMode) {
                                    // 再次判断是否是浏览模式，有可能在网络请求过程中已更改为搜索模式
                                    val newList = resourceState.data ?: emptyList()
                                    _pokemonItems.update {
                                        newList
                                    }
                                }
                            }
                        }
                    }
            }
        }
    }

    /**
     * 在搜索模式下，查询结果
     */
    private fun searchResult(nameOrId: String) {
        searchJob?.cancel()
        loadPageJob?.cancel()
        searchJob = viewModelScope.launch {
            // NOTE 防止频繁请求
            delay(100)
            pokemonRepo.getPokemonInfoByNameOrId(nameOrId)
                .collect { resourceState: ResourceState<List<PokemonInfoUI>> ->
                    when (resourceState) {
                        is ResourceState.Error -> {
                            if (screenMode is HomeScreenMode.SearchMode) {
                                val message = resourceState.message!!
                                _pokemonItems.update {
                                    emptyList()
                                }
                            }
                        }
                        is ResourceState.Loading -> {

                        }
                        is ResourceState.Success -> {
                            if (screenMode is HomeScreenMode.SearchMode) {
                                val list = resourceState.data ?: emptyList()
                                _pokemonItems.update {
                                    list
                                }
                            }
                        }
                    }
                }
        }
    }
}