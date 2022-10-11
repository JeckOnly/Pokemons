package com.jeckonly.pokemons.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeckonly.core_data.common.repo.impl.PokemonRepoImpl
import com.jeckonly.core_data.common.repo.interface_.PokemonRepo
import com.jeckonly.core_model.ui.PokemonInfoUI
import com.jeckonly.pokemons.ui_event.home.HomeEvent
import com.jeckonly.pokemons.ui_event.splash.SplashEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val pokemonRepo: PokemonRepo
) : ViewModel() {


    fun onEvent(event: HomeEvent) {
        when (event) {

            else -> {}
        }
    }
}