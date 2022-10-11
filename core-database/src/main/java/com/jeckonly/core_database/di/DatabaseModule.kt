package com.jeckonly.core_database.di

import android.app.Application
import androidx.room.Room
import com.jeckonly.core_database.dao.PokemonInfoEntityDao
import com.jeckonly.core_database.database.PokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providePokemonDatabase(app: Application): PokemonDatabase {
        return Room.databaseBuilder(
            app,
            PokemonDatabase::class.java, "pokemon-database"
        ).build()
    }

    @Provides
    @Singleton
    fun providePokemonInfoEntityDao(database: PokemonDatabase): PokemonInfoEntityDao {
        return database.pokemonInfoEntityDao()
    }
}