package com.jeckonly.pokemons.presentation.home

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.jeckonly.pokemons.navigation.PokeNavDestination

@Composable
fun HomeRoute(navController: NavController) {
    Button(onClick = {
        navController.navigate(PokeNavDestination.DetailDestination.route)
    }) {
        Text(text = "jump to detail")
    }
}