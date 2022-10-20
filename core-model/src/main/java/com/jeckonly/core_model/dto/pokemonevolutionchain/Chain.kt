package com.jeckonly.core_model.dto.pokemonevolutionchain

import com.jeckonly.core_model.dto.pokemondetail.Species


/**
 * 第一层
 */
data class Chain(
//    val evolution_details: List<EvolutionDetail>,

    /**
     * 列表可能为空
     */
    val evolves_to: List<EvolvesTo>,
    val is_baby: Boolean,
    val species: Species
)