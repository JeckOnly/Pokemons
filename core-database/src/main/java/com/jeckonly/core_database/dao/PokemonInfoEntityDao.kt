package com.jeckonly.core_database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jeckonly.core_model.entity.pokemonlistitem.PokemonInfoEntity

@Dao
interface PokemonInfoEntityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonList(pokemonList: List<PokemonInfoEntity>)

    @Query("SELECT * FROM PokemonInfoEntity WHERE page = :page_")
    suspend fun getPokemonListOnePage(page_: Int): List<PokemonInfoEntity>

    @Query("SELECT * FROM PokemonInfoEntity WHERE page <= :page_")
    suspend fun getAllPokemonListLessThanPage(page_: Int): List<PokemonInfoEntity>
}