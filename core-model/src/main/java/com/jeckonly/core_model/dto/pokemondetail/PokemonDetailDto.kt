package com.jeckonly.core_model.dto.pokemondetail

/**
 * api返回的宝可梦模型
 * 这个数据模型并不包含所有字段，即在映射到UI层之前就已做了删减，主要是很多和版本有关的无用详细信息
 */
data class PokemonDetailDto(
    val abilities: List<Ability>,
    val base_experience: Int,
    val game_indices: List<GameIndice>,
    val height: Int,
    val held_items: List<HeldItem>,
    val id: Int,
    val is_default: Boolean,
    val location_area_encounters: String,
    val moves: List<Move>,
    val name: String,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
)