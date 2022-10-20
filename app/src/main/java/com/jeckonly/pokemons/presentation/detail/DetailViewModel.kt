package com.jeckonly.pokemons.presentation.detail

import android.app.Application
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
import com.jeckonly.core_data.download.DownloadNotificationClient
import com.jeckonly.core_model.domain.ResourceState
import com.jeckonly.core_model.ui.detail.PokemonDetailUI
import com.jeckonly.core_model.ui.home.HomeScreenMode
import com.jeckonly.core_model.ui.home.PokemonInfoUI
import com.jeckonly.pokemons.R
import com.jeckonly.pokemons.ui_event.detail.DetailEvent
import com.jeckonly.pokemons.ui_event.home.HomeEvent
import com.jeckonly.util.LogUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val app: Application,
    private val pokemonRepo: PokemonRepo,
) : ViewModel() {

    private val _pokemonDetailUIStateFlow: MutableStateFlow<PokemonDetailUI?> = MutableStateFlow(
        PokemonDetailUI()
    )
    val pokemonDetailUIStateFlow: StateFlow<PokemonDetailUI?> = _pokemonDetailUIStateFlow

    fun onEvent(event: DetailEvent) {
        when(event) {
            is DetailEvent.Init -> {
                viewModelScope.launch {
                    pokemonRepo.getPokemonDetailUIByName(name = event.name).collect { state ->
                        when (state) {
                            is ResourceState.Error -> {
                                // TODO
                            }
                            is ResourceState.Loading -> {
                                // TODO
                            }
                            is ResourceState.Success -> {
                                _pokemonDetailUIStateFlow.update {
                                    state.data?:it
                                }
                                LogUtil.d(state.data.toString())
                            }
                        }
                    }
                }
            }
        }
    }
}