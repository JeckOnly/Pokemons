package com.jeckonly.pokemons.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeckonly.core_model.ui.PokemonInfoUI
import com.jeckonly.pokemons.ui_event.home.HomeEvent
import com.jeckonly.pokemons.ui_event.splash.SplashEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : ViewModel() {

    val fakePokemonInfoItems = listOf<PokemonInfoUI>(
        PokemonInfoUI(name = "spearow", "https://pokeapi.co/api/v2/pokemon/21/"),
        PokemonInfoUI(name = "fearow", "https://pokeapi.co/api/v2/pokemon/22/"),
        PokemonInfoUI(name = "ekans", "https://pokeapi.co/api/v2/pokemon/23/"),
        PokemonInfoUI(name = "arbok", "https://pokeapi.co/api/v2/pokemon/24/"),
        PokemonInfoUI(name = "pikachu", "https://pokeapi.co/api/v2/pokemon/25/"),
    )
    fun onEvent(event: HomeEvent) {
        when (event) {

            else -> {}
        }
    }
}