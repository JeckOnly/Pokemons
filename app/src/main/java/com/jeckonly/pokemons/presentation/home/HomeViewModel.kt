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
import kotlinx.coroutines.flow.*
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


    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.WantMoreItem -> {
                loadMorePage()
            }
        }
    }

    fun onSearchTextChanged(newValue: String) {
        searchText = newValue
        if (newValue.isEmpty()) {
            mScreenMode = HomeScreenMode.BrowseMode
        } else {
            // not empty
            if (mScreenMode is HomeScreenMode.BrowseMode) {
                // prevent change it often
                mScreenMode = HomeScreenMode.SearchMode
            }
        }
    }

    /**
     * 在浏览模式下，加载更多页面
     */
    private fun loadMorePage() {
        LogUtil.d("loadMorePage")
        if (mScreenMode is HomeScreenMode.BrowseMode) {
            // 浏览模式才请求更多
            val newPage = mPage.value + 1
            viewModelScope.launch {
                pokemonRepo.getAllItemLessThanPage(newPage).collect { resourceState: ResourceState<List<PokemonInfoUI>> ->
                    when(resourceState) {
                        is ResourceState.Error -> {

                        }
                        is ResourceState.Loading -> {

                        }
                        is ResourceState.Success -> {
                            // 只有成功的情况才增加页数啊
                            mPage.update {
                                newPage
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
}