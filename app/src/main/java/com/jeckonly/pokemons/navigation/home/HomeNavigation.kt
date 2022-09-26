package com.jeckonly.pokemons.navigation.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeckonly.pokemons.navigation.PokeNavDestination
import com.jeckonly.pokemons.presentation.home.HomeRoute
import com.jeckonly.pokemons.presentation.splash.SplashRoute

fun NavGraphBuilder.homeGraph(navController: NavController) {
    composable(route = PokeNavDestination.HomeDestination.route) {
        HomeRoute(navController)
    }
}