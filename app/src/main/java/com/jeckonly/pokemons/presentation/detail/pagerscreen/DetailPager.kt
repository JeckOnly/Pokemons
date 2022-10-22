package com.jeckonly.pokemons.presentation.detail.pagerscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeckonly.core_model.ui.detail.PokemonDetailUI
import com.jeckonly.pokemons.design.component.pokemon.PokemonDetailItem
import com.jeckonly.pokemons.design.theme.Blue10

@Composable
fun DetailPager(pokemonDetailUI: PokemonDetailUI, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        PokemonDetailItem(
            itemName = "Experience", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            Text(text = pokemonDetailUI.experience.toString(), fontSize = 16.sp, color = Blue10)
        }
        PokemonDetailItem(
            itemName = "Abilities", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            Text(text = pokemonDetailUI.abilities.reduceOrNull { str1, str2 ->
                "$str1, $str2"
            }?:"", fontSize = 16.sp, color = Blue10)
        }
        PokemonDetailItem(
            itemName = "Held Items", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            Text(text = pokemonDetailUI.heldItems.reduceOrNull { str1, str2 ->
                "$str1, $str2"
            }?:"", fontSize = 16.sp, color = Blue10)
        }
        PokemonDetailItem(
            itemName = "Moves", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            Text(text = pokemonDetailUI.moves.reduceOrNull { str1, str2 ->
                "$str1, $str2"
            }?:"", fontSize = 16.sp, color = Blue10)
        }
        PokemonDetailItem(
            itemName = "Games", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            Text(text = pokemonDetailUI.games.reduceOrNull { str1, str2 ->
                "$str1, $str2"
            }?:"", fontSize = 16.sp, color = Blue10)
        }
    }
}