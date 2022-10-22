package com.jeckonly.pokemons.design.component.pokemon

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeckonly.pokemons.design.theme.Gray10

@Composable
fun PokemonDetailItem(itemName: String, modifier: Modifier = Modifier, itemContent: @Composable () -> Unit) {
    Box (
        modifier = modifier.padding(start = 20.dp, end = 20.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Text(text = itemName, color = Gray10, fontSize = 13.sp)
        Box(modifier = Modifier.padding(start = 110.dp)) {
            itemContent()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPokemonDetailItem() {
    PokemonDetailItem("Egg Groups", Modifier.fillMaxWidth()){
        Text(text = "dfdsfasdfasdfasdf")
    }
}