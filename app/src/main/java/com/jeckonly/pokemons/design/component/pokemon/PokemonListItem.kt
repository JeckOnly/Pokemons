package com.jeckonly.pokemons.design.component.pokemon

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import coil.compose.AsyncImage
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.jeckonly.core_model.ui.PokemonInfoUI
import com.jeckonly.pokemons.R
import com.jeckonly.util.LogUtil

//@Composable
//fun PokemonListItem(pokemonInfoUI: PokemonInfoUI, modifier: Modifier = Modifier) {
//
//    var rgbBackgroundColor by remember {
//        mutableStateOf(Color(0xffffffff))
//    }
//
//    Surface(
//        modifier = modifier,
//        color = rgbBackgroundColor
//    ) {
//        ConstraintLayout(Modifier.fillMaxSize()) {
//            val (pokemonImage, pokemonName) = createRefs()
//
//            AsyncImage(
//                model = ImageRequest.Builder(LocalContext.current)
//                    .allowHardware(false)
//                    .data(pokemonInfoUI.artworkUrl)
//                    .crossfade(true)
//                    .listener(
//                        onSuccess = { _: ImageRequest, result: SuccessResult ->
//                            Palette.Builder(result.drawable.toBitmap()).generate { palette ->
//                                val hsl = palette?.dominantSwatch?.hsl
//                                if (hsl != null) {
//                                    LogUtil.d("主要颜色：$hsl")
//                                    rgbBackgroundColor = Color.hsl(hue = hsl[0], saturation = hsl[1], lightness = hsl[2])
//                                }
//                            }
//                        }
//                    )
//                    .build(),
//                placeholder = painterResource(id = R.drawable.placeholder),
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier.constrainAs(pokemonImage) {
//                    start.linkTo(parent.start)
//                    end.linkTo(parent.end)
//                    top.linkTo(parent.top)
//                    width = Dimension.fillToConstraints
//                    height = Dimension.ratio("1:1")
//
//                }
//            )
//
//            Text(
//                text = pokemonInfoUI.name,
//                color = MaterialTheme.colors.primary,
//                modifier = Modifier.constrainAs(pokemonName) {
//                    start.linkTo(parent.start)
//                    end.linkTo(parent.end)
//                    top.linkTo(pokemonImage.bottom)
//                    bottom.linkTo(parent.bottom)
//                    width = Dimension.wrapContent
//                    height = Dimension.wrapContent
//                }
//            )
//        }
//    }
//}
@Composable
fun PokemonListItem(pokemonInfoUI: PokemonInfoUI, modifier: Modifier = Modifier) {

    var rgbBackgroundColor by remember {
        mutableStateOf(Color(0xffffffff))
    }

    Surface(
        modifier = modifier,
        color = rgbBackgroundColor
    ) {
        Column() {
            Spacer(modifier = Modifier.height(7.dp))
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .allowHardware(false)
                    .data(pokemonInfoUI.artworkUrl)
                    .crossfade(true)
                    .listener(
                        onSuccess = { _: ImageRequest, result: SuccessResult ->
                            // 加载图片成功后回调设置背景颜色
                            Palette.Builder(result.drawable.toBitmap()).generate { palette ->
                                val hsl = palette?.dominantSwatch?.hsl
                                if (hsl != null) {
                                    LogUtil.d("主要颜色：$hsl")
                                    rgbBackgroundColor = Color.hsl(
                                        hue = hsl[0],
                                        saturation = hsl[1],
                                        lightness = hsl[2]
                                    )
                                }
                            }
                        }
                    )
                    .build(),
                placeholder = painterResource(id = R.drawable.placeholder),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(3.5f)
                    .aspectRatio(1f)
                    .align(Alignment.CenterHorizontally)
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = pokemonInfoUI.name,
                    color = MaterialTheme.colors.primary
                )
            }
            Spacer(modifier = Modifier.height(7.dp))
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xffffff)
@Composable
fun PreviewPokemonListItem() {
    val pokemonInfoUI = remember {
        PokemonInfoUI(
            "spearow",
            "https://pokeapi.co/api/v2/pokemon/21/"
        )
    }
    PokemonListItem(
        pokemonInfoUI,
        Modifier
            .width(100.dp)
            .height(130.dp)
    )
}