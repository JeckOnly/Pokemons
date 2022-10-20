package com.jeckonly.core_model.mapper.pokemonlistitem

import com.jeckonly.core_model.dto.pokemonlistitem.PokemonInfoDto
import com.jeckonly.core_model.dto.pokemonlistitem.getIdFromUrl
import com.jeckonly.core_model.entity.pokemonlistitem.PokemonInfoEntity
import com.jeckonly.core_model.ui.home.PokemonInfoUI

/**
 * 这个函数不应该被使用，应遵循单一数据来源
 */
fun PokemonInfoDto.toPokemonInfoUI(): PokemonInfoUI {
    return PokemonInfoUI(
        name = name,
        url = url,
        id = getIdFromUrl(url)
    )
}

fun PokemonInfoDto.toPokemonInfoEntity(page: Int): PokemonInfoEntity {
    return PokemonInfoEntity(
        name = name,
        url = url,
        page = page,
        id = getIdFromUrl(url)
    )
}

fun PokemonInfoEntity.toPokemonInfoUI(): PokemonInfoUI {
    return PokemonInfoUI(
        name = name,
        url = url,
        id = id
    )
}