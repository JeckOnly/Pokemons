package com.jeckonly.pokemons.navigation.splash

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeckonly.pokemons.navigation.PokeNavDestination
import com.jeckonly.pokemons.presentation.splash.SplashRoute

fun NavGraphBuilder.splashGraph(navController: NavController) {
    composable(route = PokeNavDestination.SplashDestination.route) {
        SplashRoute(navController)
    }
}