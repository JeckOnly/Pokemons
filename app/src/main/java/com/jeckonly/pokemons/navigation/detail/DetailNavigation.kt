package com.jeckonly.pokemons.navigation.detail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeckonly.pokemons.navigation.PokeNavDestination
import com.jeckonly.pokemons.presentation.detail.DetailRoute
import com.jeckonly.pokemons.presentation.splash.SplashRoute

fun NavGraphBuilder.detailGraph() {
    composable(route = PokeNavDestination.DetailDestination.route) {
        DetailRoute()
    }
}