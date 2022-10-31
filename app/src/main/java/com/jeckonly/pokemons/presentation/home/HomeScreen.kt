package com.jeckonly.pokemons.presentation.home

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeckonly.core_model.dto.NetworkConstant
import com.jeckonly.core_model.dto.pokemondetail.Home
import com.jeckonly.core_model.ui.home.PokemonInfoUI
import com.jeckonly.pokemons.R
import com.jeckonly.pokemons.design.component.button.UpwardFloatingActionButton
import com.jeckonly.pokemons.design.component.dialog.TitleBody1ButtonDialog
import com.jeckonly.pokemons.design.component.dialog.TitleBody2ButtonDialog
import com.jeckonly.pokemons.design.component.pokemon.PokemonListItem
import com.jeckonly.pokemons.design.component.textfield.SearchBar
import com.jeckonly.pokemons.design.theme.Blue10
import com.jeckonly.pokemons.design.theme.Blue8
import com.jeckonly.pokemons.ui_event.home.HomeEvent
import com.jeckonly.util.LogUtil
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@Composable
fun HomeRoute(onCLickPokemon: (String, Int) -> Unit, modifier: Modifier = Modifier, viewModel: HomeViewModel = hiltViewModel()) {

    val pokemonItems = viewModel.pokemonItems.collectAsState()
    val hasDownload = viewModel.hasFinishDownloadStateFlow.collectAsState()
    val isRunningDownloadState = viewModel.isRunningDownloadState.collectAsState()
    HomeScreen(
        searchText = viewModel.searchText,
        listState = viewModel.listState,
        onSearchTextChanged = viewModel::onSearchTextChanged,// NOTE 优化性能，避免创建lambda对象
        onItemsTooFewRemaining = viewModel::onWantMoreItemEvent,
        pokemonItems = pokemonItems.value,
        hasDownload = hasDownload.value,
        isRunningDownload = isRunningDownloadState.value,
        onClickDownload = viewModel::onClickDownloadEvent,
        onCLickPokemon = onCLickPokemon,
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
    hasDownload: Boolean,
    isRunningDownload: Boolean,
    onClickDownload: () -> Unit,
    onCLickPokemon: (String, Int) -> Unit,
    modifier: Modifier = Modifier
) {

    var showDialog by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    val firstItemVisible by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex == 0
        }
    }

    val scope = rememberCoroutineScope()

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
                    if (isRunningDownload) {
                        Toast.makeText(context, context.getString(R.string.downloading), Toast.LENGTH_SHORT).show()
                    } else {
                        showDialog = true
                    }
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
            ) {
                items(
                    items = pokemonItems,
                    key = { item: PokemonInfoUI ->
                        item.name
                    }
                ) { item ->
                    PokemonListItem(
                        pokemonInfoUI = item,
                        onCLickPokemon = onCLickPokemon,
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
        UpwardFloatingActionButton(
            visible = !firstItemVisible,
            onClick = {
                scope.launch {
                    listState.animateScrollToItem(0, 0)
                }
            }
        )
    }
    HomeScreenDialog(showDialog = showDialog, hasDownload = hasDownload, onClickDownload = onClickDownload) {
        showDialog = false
    }
}

@Composable
fun HomeScreenDialog(showDialog: Boolean, hasDownload: Boolean, onClickDownload: () -> Unit, onDismissRequest: () -> Unit) {
    if (showDialog) {
        if (hasDownload) {
            TitleBody1ButtonDialog(
                title = {
                    Text(
                        text = stringResource(id = R.string.fuzzy_mode),
                        color = Blue10,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                body = {
                    Text(
                        text = stringResource(id = R.string.fuzzy_mode_words),
                        color = Blue8,
                        fontSize = 14.sp
                    )
                },
                button = {
                    Button(
                        onClick = {
                            onDismissRequest()
                        }, colors = ButtonDefaults.buttonColors(
                            backgroundColor = Blue10,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(text = stringResource(id = R.string.get_it))
                    }
                }) {
                onDismissRequest()
            }
        } else {
            TitleBody2ButtonDialog(
                title = {
                    Text(
                        text = stringResource(id = R.string.exact_mode),
                        color = Blue10,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                body = {
                    Text(
                        text = stringResource(id = R.string.exact_mode_words),
                        color = Blue8,
                        fontSize = 14.sp
                    )
                },
                buttonLeft = {
                    Text(text = stringResource(id = R.string.later), color = Blue10, fontSize = 14.sp, modifier = Modifier.clickable {
                        onDismissRequest()
                    })
                },
                buttonRight = {
                    Button(
                        onClick = {
                            onClickDownload()
                            onDismissRequest()
                        }, colors = ButtonDefaults.buttonColors(
                            backgroundColor = Blue10,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(text = stringResource(id = R.string.download))
                    }
                }) {
                onDismissRequest()
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
        searchText = "",
        listState = listState,
        onSearchTextChanged = { },
        onItemsTooFewRemaining = { },
        pokemonItems = fakePokemonInfoItems,
        hasDownload = false,
        isRunningDownload = false,
        onClickDownload = {},
        onCLickPokemon = { name, id ->

        },
        modifier = Modifier
    )
}