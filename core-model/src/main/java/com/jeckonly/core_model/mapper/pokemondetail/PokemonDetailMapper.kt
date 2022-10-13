package com.jeckonly.core_model.mapper.pokemondetail

import com.jeckonly.core_model.dto.pokemondetail.PokemonDetailDto
import com.jeckonly.core_model.ui.home.PokemonInfoUI


fun PokemonDetailDto.toPokemonInfoUI(): PokemonInfoUI {
    return PokemonInfoUI(
        name = name,
        url = "https://pokeapi.co/api/v2/pokemon/${id}/",
        id = id
    )
}