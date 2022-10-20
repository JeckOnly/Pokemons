package com.jeckonly.pokemons.design.component.pokemon

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.jeckonly.core_data.R
import com.jeckonly.pokemons.design.theme.Blue10
import com.jeckonly.pokemons.design.theme.Blue8

@Composable
fun PokemonDetailTitleBar(name: String, id: Int, onClickBack: () -> Unit, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colors.surface
    ) {
        ConstraintLayout {
            val (icon, title, idText) = createRefs()
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = null,
                tint = Blue8,
                modifier = Modifier.constrainAs(icon) {
                    start.linkTo(parent.start, 20.dp)
                    top.linkTo(parent.top)
                    width = Dimension.value(30.dp)
                    height = Dimension.value(30.dp)
                }.clickable {
                    onClickBack()
                }
            )
            
            Text(
                text = name,
                color = Blue10,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(title) {
                    top.linkTo(icon.top)
                    bottom.linkTo(icon.bottom)
                    start.linkTo(icon.end)
                    end.linkTo(parent.end, 50.dp)
                })

            Text(
                text = id.toString(),
                color = Blue8,
                fontSize = 14.sp,
                modifier = Modifier.constrainAs(idText) {
                    start.linkTo(title.start)
                    end.linkTo(title.end)
                    top.linkTo(title.bottom, 4.dp)
                })
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPokemonTitle() {
    PokemonDetailTitleBar(name = "pikachu", id = 25, onClickBack = {}, modifier = Modifier.fillMaxWidth())
}