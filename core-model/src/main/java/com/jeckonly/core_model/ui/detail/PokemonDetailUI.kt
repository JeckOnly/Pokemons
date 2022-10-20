package com.jeckonly.core_model.ui.detail

import com.jeckonly.core_model.dto.pokemondetail.Stat

data class PokemonDetailUI(
    val species: String = "",
    val abilities: List<String> = emptyList(),
    val heldItems: List<String> = emptyList(),
    val moves: List<String> = emptyList(),
    val types: List<String> = emptyList(),
    val games: List<String> = emptyList(),
    val stats: List<Stat> = emptyList(),
    val experience: Int = 0,
    val height: Int = 0,
    val weight: Int = 0,
    val shape: String = "",
    val captureRate: Int = 0,
    val eggGroups: List<String> = emptyList(),
    val malePercent: String = "",
    val femalePercent: String = "",
    val habitat: String = "",
    )
