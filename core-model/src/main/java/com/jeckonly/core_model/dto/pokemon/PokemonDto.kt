package com.jeckonly.core_model.dto.pokemon

/**
 * api返回的宝可梦模型
 */
data class PokemonDto(
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