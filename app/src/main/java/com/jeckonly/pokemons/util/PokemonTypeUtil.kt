package com.jeckonly.pokemons.util

import androidx.compose.ui.graphics.Color
import com.jeckonly.pokemons.design.theme.*

object PokemonTypeUtil {
    fun getTypeColor(type: String): Color {
        return when (type) {
            "fighting" -> Fighting
            "flying" -> Flying
            "poison" -> Poison
            "ground" -> Ground
            "rock" -> Rock
            "bug" -> Bug
            "ghost" -> Ghost
            "steel" -> Steel
            "fire" -> Fire
            "water" -> Water
            "grass" -> Grass
            "electric" -> Electric
            "psychic" -> Psychic
            "ice" -> Ice
            "dragon" -> Dragon
            "fairy" -> Fairy
            "dark" -> Dark
            else -> Gray10
        }
    }
}