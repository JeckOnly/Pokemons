package com.jeckonly.pokemons.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

sealed class PokeNavDestination(val route: String){
    object HomeDestination: PokeNavDestination("home_destination")
    object DetailDestination: PokeNavDestination("Detail_destination")
}
