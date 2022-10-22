package com.jeckonly.pokemons.design.component.pokemon

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeckonly.pokemons.util.PokemonTypeUtil

@Composable
fun PokemonTypeItem(type: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(40),
        color = PokemonTypeUtil.getTypeColor(type),
    ) {
        Text(
            text = type,
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier.padding(horizontal = 19.dp, vertical = 5.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xffffffff)
@Composable
fun PreviewPokemonTypeItem() {
    PokemonTypeItem("dark")
}