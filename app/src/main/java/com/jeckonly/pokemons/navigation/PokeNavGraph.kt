package com.jeckonly.pokemons.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.jeckonly.pokemons.navigation.detail.detailGraph
import com.jeckonly.pokemons.navigation.home.homeGraph

@Composable
fun PokeNavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = PokeNavDestination.HomeDestination.route,
        modifier = modifier
    ) {
        homeGraph()
        detailGraph()
    }
}