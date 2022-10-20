package com.jeckonly.pokemons.design.component.pokemon

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.palette.graphics.Palette
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.jeckonly.core_data.R
import com.jeckonly.core_model.ui.home.getArtWorkUrlFormId
import com.jeckonly.util.LogUtil

@Composable
fun PokemonDetailBigImage(url: String, modifier: Modifier = Modifier) {

    var rgbBackgroundColor by remember {
        mutableStateOf(Color(0xffffffff))
    }
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        color = rgbBackgroundColor
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .allowHardware(false)
                .data(url)
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
                .padding(15.dp)
                .aspectRatio(1f)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPokemonDetailBigImage() {
    PokemonDetailBigImage(
        getArtWorkUrlFormId(25),
        Modifier
            .fillMaxWidth()
            .padding(start = 60.dp, end = 60.dp)
            .aspectRatio(0.9f)
    )
}