package com.jeckonly.core_model.dto.pokemondetail

/**
 * @param is_hidden 是否隐藏能力
 */
data class Ability(
    val ability: AbilityDetail,
    val is_hidden: Boolean,
    val slot: Int
)