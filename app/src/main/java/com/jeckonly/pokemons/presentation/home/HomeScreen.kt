package com.jeckonly.pokemons.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeckonly.core_model.ui.PokemonInfoUI
import com.jeckonly.pokemons.R
import com.jeckonly.pokemons.design.component.pokemon.PokemonListItem
import com.jeckonly.pokemons.design.component.textfield.SearchBar
import com.jeckonly.pokemons.design.theme.Blue8
import com.jeckonly.util.LogUtil

@Composable
fun HomeRoute(modifier: Modifier = Modifier, viewModel: HomeViewModel = hiltViewModel()) {

    HomeScreen(modifier = modifier)
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {

    // TODO 状态处理
    var text by remember {
        mutableStateOf("")
    }
    val listState = rememberLazyGridState()

    val fakePokemonInfoItems = remember {
        listOf<PokemonInfoUI>(
            PokemonInfoUI(name = "spearow", "https://pokeapi.co/api/v2/pokemon/21/", 21),
            PokemonInfoUI(name = "fearow", "https://pokeapi.co/api/v2/pokemon/22/", 22),
            PokemonInfoUI(name = "ekans", "https://pokeapi.co/api/v2/pokemon/23/", 23),
            PokemonInfoUI(name = "arbok", "https://pokeapi.co/api/v2/pokemon/24/", 24),
            PokemonInfoUI(name = "pikachu", "https://pokeapi.co/api/v2/pokemon/25/", 25),
        )
    }

    Surface(
        modifier = modifier
            .fillMaxSize(),
        color = MaterialTheme.colors.surface,
    ) {
        Column() {
            // Title
            Text(
                text = stringResource(id = R.string.app_name),
                color = MaterialTheme.colors.onSurface,
                fontSize = 43.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(
                        start = 20.dp,
                        top = 30.dp
                    )
            )
            Spacer(modifier = Modifier.height(6.dp))
            // the word under title
            Text(
                text = stringResource(id = R.string.how_to_search),
                color = Blue8,
                fontSize = 14.sp,
                modifier = Modifier
                    .align(Alignment.Start)
                    .fillMaxWidth()
                    .padding(
                        start = 20.dp,
                        end = 20.dp
                    )
            )
            Spacer(modifier = Modifier.height(20.dp))
            // search edit text
            SearchBar(
                text = text,
                onValueChange = {
                    text = it
                },
                onHelpIconClicked = {
                    LogUtil.d("help icon clicked")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 20.dp,
                        end = 20.dp
                    )
                    .wrapContentHeight()
            )
            Spacer(modifier = Modifier.height(45.dp))
            // pokemon list
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                state = listState,
                contentPadding = PaddingValues(start = 10.dp, end = 10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .navigationBarsPadding()
            ) {
                items(
                    items = fakePokemonInfoItems,
                    key = { item: PokemonInfoUI ->
                        item.name
                    }
                ) { item ->
                    PokemonListItem(
                        pokemonInfoUI = item,
                        modifier = Modifier
                            .padding(
                                start = 10.dp,
                                end = 10.dp,
                                bottom = 20.dp
                            )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}