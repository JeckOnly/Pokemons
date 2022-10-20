package com.jeckonly.pokemons.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

sealed class PokeNavDestination(val route: String){
    object HomeDestination: PokeNavDestination("home_destination")
    object DetailDestination: PokeNavDestination("Detail_destination/{name}/{id}") {
        fun getNavigationRoute(name: String, id: Int): String{
            return "Detail_destination/${name}/${id}"
        }
    }
}
