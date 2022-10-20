package com.jeckonly.pokemons.navigation.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeckonly.pokemons.navigation.PokeNavDestination
import com.jeckonly.pokemons.presentation.home.HomeRoute

fun NavGraphBuilder.homeGraph(onClickPokemon: (String, Int) -> Unit) {
    composable(route = PokeNavDestination.HomeDestination.route) {
        HomeRoute(onClickPokemon)
    }
}