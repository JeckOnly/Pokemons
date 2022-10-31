package com.jeckonly.pokemons.navigation.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jeckonly.pokemons.design.theme.PokemonsTheme
import com.jeckonly.pokemons.navigation.PokeNavGraph

@Composable
fun PokeApp() {
    // Remember a SystemUiController
    val systemUiController = rememberSystemUiController()

    DisposableEffect(key1 = systemUiController) {
        systemUiController.setStatusBarColor(
            color = Transparent,
            darkIcons = true
        )
        onDispose {

        }
    }

    PokemonsTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
           PokeNavGraph(modifier = Modifier
               .fillMaxSize()
               .statusBarsPadding()
               .navigationBarsPadding()
           )
        }
    }
}