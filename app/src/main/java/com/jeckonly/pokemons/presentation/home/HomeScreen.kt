package com.jeckonly.pokemons.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeckonly.core_model.dto.NetworkConstant
import com.jeckonly.core_model.ui.home.PokemonInfoUI
import com.jeckonly.pokemons.R
import com.jeckonly.pokemons.design.component.pokemon.PokemonListItem
import com.jeckonly.pokemons.design.component.textfield.SearchBar
import com.jeckonly.pokemons.design.theme.Blue8
import com.jeckonly.pokemons.ui_event.home.HomeEvent
import com.jeckonly.util.LogUtil
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

@Composable
fun HomeRoute(modifier: Modifier = Modifier, viewModel: HomeViewModel = hiltViewModel()) {

    val pokemonItems = viewModel.pokemonItems.collectAsState()
    HomeScreen(
        searchText = viewModel.searchText,
        listState = viewModel.listState,
        onSearchTextChanged = viewModel::onSearchTextChanged,
        onItemsTooFewRemaining = {
            viewModel.onEvent(HomeEvent.WantMoreItem)
        },
        pokemonItems = pokemonItems.value,
        modifier = modifier
    )
}

@Composable
fun HomeScreen(
    searchText: String,
    listState: LazyGridState,
    onSearchTextChanged: (String) -> Unit,
    onItemsTooFewRemaining: () -> Unit,
    pokemonItems: List<PokemonInfoUI>,
    modifier: Modifier = Modifier
) {


    LaunchedEffect(key1 = listState, block = {
        //distinctUntilChanged过滤掉连续相同值，其实相当于做了缓冲
        snapshotFlow {
            listState.firstVisibleItemIndex
        }.map{ firstIndex ->
            LogUtil.d("first visible index: $firstIndex")
            LogUtil.d("in size: ${listState.layoutInfo.totalItemsCount}")
            // 第一次进入时size为0，向下传true，达到初始化目的
            firstIndex > (listState.layoutInfo.totalItemsCount - (NetworkConstant.PAGING_SIZE / 2))
        }.distinctUntilChanged()
        .filter {
            it // 在false -> true和true -> false这两个变化之中只接收false -> true这个变化
        }
        .collect {
            onItemsTooFewRemaining()
        }
    })
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
                text = searchText,
                onValueChange = onSearchTextChanged,
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
                    items = pokemonItems,
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
    val fakePokemonInfoItems = remember {
        listOf<PokemonInfoUI>(
            PokemonInfoUI(name = "spearow", "https://pokeapi.co/api/v2/pokemon/21/", 21),
            PokemonInfoUI(name = "fearow", "https://pokeapi.co/api/v2/pokemon/22/", 22),
            PokemonInfoUI(name = "ekans", "https://pokeapi.co/api/v2/pokemon/23/", 23),
            PokemonInfoUI(name = "arbok", "https://pokeapi.co/api/v2/pokemon/24/", 24),
            PokemonInfoUI(name = "pikachu", "https://pokeapi.co/api/v2/pokemon/25/", 25),
        )
    }
    val listState = rememberLazyGridState()
    HomeScreen(
        "",
        listState,
        {},
        {},
        fakePokemonInfoItems
    )
}