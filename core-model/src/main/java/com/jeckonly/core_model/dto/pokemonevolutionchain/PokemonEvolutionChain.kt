package com.jeckonly.core_model.dto.pokemonevolutionchain

/**
 * 只有[chain]字段有价值
 */
data class PokemonEvolutionChain(
    val baby_trigger_item: Any,
    val chain: Chain,
    val id: Int
)