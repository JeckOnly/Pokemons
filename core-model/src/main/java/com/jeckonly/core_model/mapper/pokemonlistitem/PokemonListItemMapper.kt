package com.jeckonly.core_model.mapper.pokemonlistitem

import com.jeckonly.core_model.dto.pokemonlistitem.PokemonInfoDto
import com.jeckonly.core_model.entity.pokemonlistitem.PokemonInfoEntity
import com.jeckonly.core_model.ui.PokemonInfoUI

fun PokemonInfoDto.toPokemonInfoUI(): PokemonInfoUI {
    return PokemonInfoUI(
        name, url
    )
}

fun PokemonInfoEntity.toPokemonInfoUI(): PokemonInfoUI {
    return PokemonInfoUI(
        name, url
    )
}