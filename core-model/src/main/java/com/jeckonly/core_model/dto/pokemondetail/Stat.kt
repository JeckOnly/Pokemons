package com.jeckonly.core_model.dto.pokemondetail

data class Stat(

    /**
     * 具体数值
     */
    val base_stat: Int,
    val effort: Int,
    val stat: StatDetail
)