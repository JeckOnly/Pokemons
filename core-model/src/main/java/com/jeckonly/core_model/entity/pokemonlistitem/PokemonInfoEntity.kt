package com.jeckonly.core_model.entity.pokemonlistitem

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonInfoEntity(
    @PrimaryKey val name: String,
    val url: String,
    val page: Int
)
