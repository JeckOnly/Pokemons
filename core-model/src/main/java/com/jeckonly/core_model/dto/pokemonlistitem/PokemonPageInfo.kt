package com.jeckonly.core_model.dto.pokemonlistitem

data class PokemonPageInfo(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<Result>
)