package com.jeckonly.pokemons.presentation.detail.pagerscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeckonly.core_model.ui.detail.PokemonDetailUI
import com.jeckonly.pokemons.design.component.pokemon.PokemonDetailItem

sealed class PagerScreen(val index: Int, val title: String, val content: @Composable (PokemonDetailUI) -> Unit) {
    class AboutScreen(): PagerScreen(0, "About", { pokemonDetailUI ->
        AboutPager(pokemonDetailUI = pokemonDetailUI)
    })

    class StatsScreen(): PagerScreen(1, "Stats", {
        Text(text = "statsscreen")
    })

    class DetailScreen(): PagerScreen(2, "Detail", {
        Text(text = "detailscreen")
    })

    companion object {
        val screenList = listOf<PagerScreen>(
            AboutScreen(),
            StatsScreen(),
            DetailScreen()
        )
    }
}
