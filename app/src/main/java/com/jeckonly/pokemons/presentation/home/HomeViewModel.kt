package com.jeckonly.pokemons.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeckonly.core_data.common.repo.interface_.PokemonRepo
import com.jeckonly.core_model.domain.ResourceState
import com.jeckonly.core_model.ui.home.HomeScreenMode
import com.jeckonly.core_model.ui.home.PokemonInfoUI
import com.jeckonly.pokemons.ui_event.home.HomeEvent
import com.jeckonly.util.LogUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val pokemonRepo: PokemonRepo
) : ViewModel() {

    /**
     * mode
     */
    private var mScreenMode: HomeScreenMode = HomeScreenMode.BrowseMode

    /**
     * the pokemon items to show
     */
    private val mPokemonItems: MutableStateFlow<List<PokemonInfoUI>> = MutableStateFlow(emptyList())
    val pokemonItems: StateFlow<List<PokemonInfoUI>> = mPokemonItems

    /**
     * 浏览模式下当前已加载的最大页面序号，初始值为0
     */
    private val mPage: MutableStateFlow<Int> = MutableStateFlow(0)

    /**
     * 当前搜索的字符串（name or id），若不为空则为搜索模式
     */
    var searchText by mutableStateOf("")
        private set

    /**
     * 管理搜索执行的job
     */
    var searchJob: Job? = null

    /**
     * 管理加载页数执行的job
     */
    var loadPageJob: Job? = null

    /**
     * NOTE 只能被UI层调用
     */
    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.WantMoreItem -> {
                // TODO 旧的请求未完成，新的又来了有无bug
                loadPage()
            }
        }
    }

    fun onSearchTextChanged(newValue: String) {
        searchText = newValue
        if (newValue.isEmpty()) {
            mScreenMode = HomeScreenMode.BrowseMode
            loadPage()
        } else {
            // not empty
            if (mScreenMode is HomeScreenMode.BrowseMode) {
                // prevent change it often
                mScreenMode = HomeScreenMode.SearchMode
            }
            searchResult(newValue)
        }
    }

    /**
     * 在浏览模式下，加载 <= page页的所有页面
     */
    private fun loadPage() {
        if (mScreenMode is HomeScreenMode.BrowseMode) {
            searchJob?.cancel()
            val lastJobCompleted = loadPageJob?.isCompleted ?: true
            // 如果上一个loadPage任务还在继续，就不添加新任务，这种情况发生在页面滑到底部又上滑又下滑，发送了2次以上WantMoreItem事件
            if (!lastJobCompleted) return
            loadPageJob = viewModelScope.launch {
                // NOTE 防止频繁请求
                delay(100)
                pokemonRepo.getAllItemLessThanPage(mPage.value + 1)
                    .collect { resourceState: ResourceState<List<PokemonInfoUI>> ->
                        when (resourceState) {
                            is ResourceState.Error -> {

                            }
                            is ResourceState.Loading -> {

                            }
                            is ResourceState.Success -> {
                                // 只有成功的情况才增加页数啊
                                mPage.update {
                                    it + 1
                                }
                                if (mScreenMode is HomeScreenMode.BrowseMode) {
                                    // 再次判断是否是浏览模式，有可能在网络请求过程中已更改为搜索模式
                                    val newList = resourceState.data ?: emptyList()
                                    mPokemonItems.update {
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
                            if (mScreenMode is HomeScreenMode.SearchMode) {
                                val message = resourceState.message!!
                                mPokemonItems.update {
                                    emptyList()
                                }
                            }
                        }
                        is ResourceState.Loading -> {

                        }
                        is ResourceState.Success -> {
                            if (mScreenMode is HomeScreenMode.SearchMode) {
                                val list = resourceState.data ?: emptyList()
                                mPokemonItems.update {
                                    list
                                }
                            }
                        }
                    }
                }
        }
    }
}