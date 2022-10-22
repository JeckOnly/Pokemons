package com.jeckonly.pokemons.presentation.detail.pagerscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeckonly.core_model.ui.detail.PokemonDetailUI
import com.jeckonly.core_data.R
import com.jeckonly.pokemons.design.component.pokemon.PokemonDetailItem
import com.jeckonly.pokemons.design.component.pokemon.PokemonTypeItem
import com.jeckonly.pokemons.design.theme.Blue10
import com.jeckonly.pokemons.design.theme.Psychic
import com.jeckonly.pokemons.design.theme.Water

@Composable
fun AboutPager(pokemonDetailUI: PokemonDetailUI, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        PokemonDetailItem(
            itemName = "Species", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            Text(text = pokemonDetailUI.species, fontSize = 16.sp, color = Blue10)
        }
        PokemonDetailItem(
            itemName = "Height", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            Text(text = pokemonDetailUI.height, fontSize = 16.sp, color = Blue10)
        }
        PokemonDetailItem(
            itemName = "Weight", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            Text(text = pokemonDetailUI.weight, fontSize = 16.sp, color = Blue10)
        }
        PokemonDetailItem(
            itemName = "Type", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            Row {
                for (type in pokemonDetailUI.types) {
                    PokemonTypeItem(type = type, modifier = Modifier.padding(end = 10.dp))
                }
            }
        }
        Text(
            text = "Breeding",
            color = Blue10,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 14.dp, start = 20.dp)
        )
        PokemonDetailItem(
            itemName = "Gender", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.ic_male),
                    contentDescription = null,
                    modifier = Modifier.size(23.dp).padding(end = 5.dp),
                    tint = Water
                )
                Text(text = pokemonDetailUI.malePercent, fontSize = 16.sp, color = Blue10, modifier = Modifier.padding(end = 10.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_female),
                    contentDescription = null,
                    modifier = Modifier.size(23.dp).padding(end = 5.dp),
                    tint = Psychic
                )
                Text(text = pokemonDetailUI.femalePercent, fontSize = 16.sp, color = Blue10, modifier = Modifier.padding(end = 5.dp))

            }
        }
        PokemonDetailItem(
            itemName = "Habitat", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            Text(text = pokemonDetailUI.habitat, fontSize = 16.sp, color = Blue10)
        }
        PokemonDetailItem(
            itemName = "Egg Groups", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            Text(text = pokemonDetailUI.eggGroups.reduceOrNull { str1, str2 ->
                "$str1, $str2"
            }?:"", fontSize = 16.sp, color = Blue10)
        }
    }
}