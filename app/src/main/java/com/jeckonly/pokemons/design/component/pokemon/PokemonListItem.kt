package com.jeckonly.pokemons.design.component.pokemon

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.jeckonly.core_model.ui.home.PokemonInfoUI
import com.jeckonly.core_data.R
import com.jeckonly.pokemons.design.theme.Blue10
import com.jeckonly.pokemons.util.ColorContrastUtil
import com.jeckonly.util.LogUtil

@Composable
fun PokemonListItem(pokemonInfoUI: PokemonInfoUI, onCLickPokemon: (String, Int) -> Unit, modifier: Modifier = Modifier) {

    var rgbBackgroundColor by remember {
        mutableStateOf(Color(0xffffffff))
    }

    var textColor by remember {
        mutableStateOf(Blue10)
    }

    Surface(
        modifier = modifier.clickable {
             onCLickPokemon(pokemonInfoUI.name, pokemonInfoUI.id)
        },
        shape = RoundedCornerShape(10.dp),
        color = rgbBackgroundColor
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
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

                                // NOTE 判断背景颜色和默认的文本颜色的对比度是否足够，不够的话再选择适合的颜色
                                // https://medium.com/@anthony.st91/calculate-contrast-between-two-colors-in-android-2fc0db879fa7
                                val colorIsOk = ColorContrastUtil.isColorContrastOk(Blue10.toArgb(), rgbBackgroundColor.toArgb())
                                if (!colorIsOk) {
                                    palette?.dominantSwatch?.bodyTextColor?.let {
                                        textColor = Color(it)
                                    }
                                }
                            }
                        }
                    )
                    .build(),
                placeholder = painterResource(id = R.drawable.placeholder),
                error = painterResource(id = R.drawable.placeholder),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = pokemonInfoUI.name,
                color = textColor,
                fontSize = 18.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = pokemonInfoUI.id.toString(),
                color = textColor,
                fontSize = 14.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPokemonListItem() {
    val pokemonInfoUI = remember {
        PokemonInfoUI(
            "spearowwwwwww",
            "https://pokeapi.co/api/v2/pokemon/21/",
            21
        )
    }
    PokemonListItem(
        pokemonInfoUI,
        { name, id ->

        },
        Modifier
            .width(100.dp)
            .wrapContentHeight()
    )
}