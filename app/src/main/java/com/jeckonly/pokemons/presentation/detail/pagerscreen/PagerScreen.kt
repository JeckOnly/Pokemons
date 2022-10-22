package com.jeckonly.pokemons.presentation.detail.pagerscreen

import androidx.compose.runtime.Composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerScope
import com.jeckonly.core_model.ui.detail.PokemonDetailUI

sealed class PagerScreen @OptIn(ExperimentalPagerApi::class) constructor(
    val index: Int,
    val title: String,
    val content: @Composable (PagerScope.(PokemonDetailUI) -> Unit)
) {
    class AboutScreen() : PagerScreen(0, "About", { pokemonDetailUI ->
        AboutPager(pokemonDetailUI = pokemonDetailUI)
    })

    @OptIn(ExperimentalPagerApi::class)
    class StatsScreen() : PagerScreen(1, "Stats", { pokemonDetailUI ->
        StatsPager(pageIndex = 1, pokemonDetailUI = pokemonDetailUI)
    })

    class DetailScreen() : PagerScreen(2, "Detail", { pokemonDetailUI ->
        DetailPager(pokemonDetailUI = pokemonDetailUI)
    })

    companion object {
        val screenList = listOf<PagerScreen>(
            AboutScreen(),
            StatsScreen(),
//            DetailScreen()
        )
    }
}
