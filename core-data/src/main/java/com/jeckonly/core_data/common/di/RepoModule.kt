package com.jeckonly.core_data.common.di

import com.jeckonly.core_data.common.repo.impl.PokemonRepoImpl
import com.jeckonly.core_data.common.repo.interface_.PokemonRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        pokemonRepoImpl: PokemonRepoImpl
    ): PokemonRepo

}