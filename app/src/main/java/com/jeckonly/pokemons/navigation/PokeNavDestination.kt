package com.jeckonly.pokemons.navigation

sealed class PokeNavDestination(val route: String){
    object SplashDestination: PokeNavDestination("splash_destination")
    object HomeDestination: PokeNavDestination("home_destination")
    object DetailDestination: PokeNavDestination("Detail_destination")
}
