package com.jeckonly.pokemons.design.component.pokemon

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.jeckonly.core_model.ui.detail.PokemonDetailUI
import com.jeckonly.pokemons.design.theme.Blue10
import com.jeckonly.pokemons.design.theme.Blue8
import com.jeckonly.pokemons.presentation.detail.pagerscreen.PagerScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PokemonDetailPager(pokemonDetailUI: PokemonDetailUI, modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    Column(modifier = modifier) {
        TabRow(
            // Our selected tab is our current page
            selectedTabIndex = pagerState.currentPage,
            // Override the indicator, using the provided pagerTabIndicatorOffset modifier
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                    height = 0.dp,
                    color = Color.Transparent
                )
            },
            divider = {
                TabRowDefaults.Divider(color = Blue10)
            },
            backgroundColor = MaterialTheme.colors.surface,
            modifier = Modifier
                .fillMaxWidth().padding(start = 20.dp, end = 20.dp)
        ) {
            // Add tabs for all of our pages
            PagerScreen.screenList.forEach { pagerScreen ->
                val selected = pagerState.currentPage == pagerScreen.index
                Tab(
                    text = {
                        Text(
                            pagerScreen.title,
                            color = if (selected) Blue10 else Blue8,
                            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                            fontSize = 16.sp
                        )
                    },
                    selected = selected,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerScreen.index)
                        }
                    },
                )
            }
        }

        HorizontalPager(
            count = PagerScreen.screenList.size,
            state = pagerState,
            modifier = Modifier.fillMaxWidth().navigationBarsPadding().padding(bottom = 20.dp)
        ) { page ->
            PagerScreen.screenList[page].content(pokemonDetailUI)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, backgroundColor = 0xffffffff)
@Composable
fun PreviewPokemonDetailPager() {
    PokemonDetailPager(
        pokemonDetailUI = PokemonDetailUI(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
    )
}


