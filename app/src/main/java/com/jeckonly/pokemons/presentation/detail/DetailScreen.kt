package com.jeckonly.pokemons.presentation.detail

import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.palette.graphics.Palette
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.jeckonly.core_data.R
import com.jeckonly.core_model.ui.detail.PokemonDetailUI
import com.jeckonly.core_model.ui.home.getArtWorkUrlFormId
import com.jeckonly.pokemons.design.component.pokemon.PokemonDetailBigImage
import com.jeckonly.pokemons.design.component.pokemon.PokemonDetailPager
import com.jeckonly.pokemons.design.component.pokemon.PokemonDetailTitleBar
import com.jeckonly.pokemons.ui_event.detail.DetailEvent
import com.jeckonly.util.LogUtil

@Composable
fun DetailRoute(
    name: String,
    id: Int,
    onClickBack: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = Unit, block = {
        LogUtil.d("name: $name, id: $id")
        viewModel.onInitEvent(name = name)
    })
    val pokemonDetailUI = viewModel.pokemonDetailUIStateFlow.collectAsState()

    DetailScreen(
        name = name,
        id = id,
        pokemonDetailUI = pokemonDetailUI.value,
        onClickBack = onClickBack
    )
}

@Composable
fun DetailScreen(
    name: String,
    id: Int,
    pokemonDetailUI: PokemonDetailUI,
    onClickBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxSize(),
        color = MaterialTheme.colors.surface,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            PokemonDetailTitleBar(
                name = name,
                id = id,
                onClickBack = onClickBack,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))
            PokemonDetailBigImage(
                getArtWorkUrlFormId(id),
                Modifier
                    .fillMaxWidth()
                    .padding(start = 60.dp, end = 60.dp)
                    .aspectRatio(0.9f)
            )
            PokemonDetailPager(pokemonDetailUI = pokemonDetailUI, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDetailScreen() {
    DetailScreen(
        name = "pikachu",
        id = 25,
        pokemonDetailUI = PokemonDetailUI(),
        onClickBack = {},
    )
}