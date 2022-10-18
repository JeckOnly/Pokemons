package com.jeckonly.pokemons.design.component.pokemon

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.jeckonly.core_model.ui.home.PokemonInfoUI
import com.jeckonly.pokemons.R
import com.jeckonly.util.LogUtil

@Composable
fun PokemonListItem(pokemonInfoUI: PokemonInfoUI, modifier: Modifier = Modifier) {

    var rgbBackgroundColor by remember {
        mutableStateOf(Color(0xffffffff))
    }

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        color = rgbBackgroundColor
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            // fixme 没有网络的时候为白色，placeholder不起作用
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
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = pokemonInfoUI.name,
                color = MaterialTheme.colors.onSurface,
                fontSize = 18.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = pokemonInfoUI.id.toString(),
                color = MaterialTheme.colors.onSurface,
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
            "spearow",
            "https://pokeapi.co/api/v2/pokemon/21/",
            21
        )
    }
    PokemonListItem(
        pokemonInfoUI,
        Modifier
            .width(100.dp)
            .wrapContentHeight()
    )
}