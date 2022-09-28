package com.jeckonly.core_database.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.jeckonly.core_database.dao.PokemonInfoEntityDao
import com.jeckonly.core_database.database.PokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providePokemonDatabase(context: Context): PokemonDatabase {
        val db = Room.databaseBuilder(
            context,
            PokemonDatabase::class.java, "pokemon-database"
        ).build()
        return db
    }

    @Provides
    @Singleton
    fun providePokemonInfoEntityDao(database: PokemonDatabase): PokemonInfoEntityDao {
        return database.pokemonInfoEntityDao()
    }
}