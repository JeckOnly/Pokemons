package com.jeckonly.core_model.mapper.pokemonlistitem

import com.jeckonly.core_model.dto.pokemonlistitem.PokemonInfoDto
import com.jeckonly.core_model.entity.pokemonlistitem.PokemonInfoEntity
import com.jeckonly.core_model.ui.PokemonInfoUI

fun PokemonInfoDto.toPokemonInfoUI(): PokemonInfoUI {
    return PokemonInfoUI(
        name = name,
        url = url,
        id = id
    )
}

fun PokemonInfoDto.toPokemonInfoEntity(page: Int): PokemonInfoEntity {
    return PokemonInfoEntity(
        name = name,
        url = url,
        page = page,
        id = id
    )
}

fun PokemonInfoEntity.toPokemonInfoUI(): PokemonInfoUI {
    return PokemonInfoUI(
        name = name,
        url = url,
        id = id
    )
}