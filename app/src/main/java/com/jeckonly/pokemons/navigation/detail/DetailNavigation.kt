package com.jeckonly.pokemons.navigation.detail

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jeckonly.pokemons.navigation.PokeNavDestination
import com.jeckonly.pokemons.presentation.detail.DetailRoute

fun NavGraphBuilder.detailGraph(onClickBack: () -> Unit) {
    composable(
        route = PokeNavDestination.DetailDestination.route,
        arguments = listOf(
            navArgument("name") { type = NavType.StringType },
            navArgument("id") { type = NavType.IntType }
        )
    ) { backStackEntry ->
        val name = backStackEntry.arguments?.getString("name")
        val id = backStackEntry.arguments?.getInt("id")
        DetailRoute(name = name ?: "pikachu", id = id ?: 25, onClickBack = onClickBack)
    }
}
