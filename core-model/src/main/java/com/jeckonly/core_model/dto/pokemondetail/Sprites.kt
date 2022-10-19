package com.jeckonly.core_model.dto.pokemondetail

data class Sprites(

    /**
     * 后背照片
     */
    val back_default: String,
    val back_female: String,
    val back_shiny: String,
    val back_shiny_female: String,

    /**
     * 正面照片
     */
    val front_default: String,
    val front_female: String,
    val front_shiny: String,
    val front_shiny_female: String,
    val other: Other,
)