package com.jeckonly.core_database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jeckonly.core_database.dao.PokemonInfoEntityDao
import com.jeckonly.core_model.entity.pokemonlistitem.PokemonInfoEntity

@Database(
    entities = [PokemonInfoEntity::class, ],
    version = 1,
    exportSchema = false
)
abstract class PokemonDatabase : RoomDatabase() {

    abstract fun pokemonInfoEntityDao(): PokemonInfoEntityDao
}