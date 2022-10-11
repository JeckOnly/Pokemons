package com.jeckonly.pokemons.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

sealed class PokeNavDestination(val route: String){
    object SplashDestination: PokeNavDestination("splash_destination")
    object HomeDestination: PokeNavDestination("home_destination") {
        fun jumpToHome(navController: NavController) {
            navController.navigate(route){
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                    inclusive = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
            }
        }
    }
    object DetailDestination: PokeNavDestination("Detail_destination")
}
