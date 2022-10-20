package com.jeckonly.pokemons.presentation.detail

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.jeckonly.util.LogUtil

@Composable
fun DetailRoute(name: String, id: Int, onClickBack: () -> Unit) {
    LogUtil.d("name: $name, id: $id")
}