package com.jeckonly.core_remote.api

import com.jeckonly.core_model.dto.pokemondetail.PokemonDetailDto
import com.jeckonly.core_model.dto.pokemonevolutionchain.PokemonEvolutionChain
import com.jeckonly.core_model.dto.pokemonlistitem.PokemonPageDto
import com.jeckonly.core_model.dto.pokemonspecies.PokemonSpeciesDto
import com.jeckonly.core_remote.EndPoint
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeService {

    @GET(EndPoint.POKEMON)
    suspend fun fetchPokemonPage(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): ApiResponse<PokemonPageDto>

    @GET("${EndPoint.POKEMON}/{nameOrId}")
    suspend fun fetchPokemonDetail(@Path("nameOrId") nameOrId: String): ApiResponse<PokemonDetailDto>

    @GET("${EndPoint.POKEMON_SPECIES}/{nameOrId}")
    suspend fun fetchPokemonSpecies(@Path("nameOrId") nameOrId: String): ApiResponse<PokemonSpeciesDto>

    @GET("${EndPoint.EVOLUTION_CHAIN}/{id}")
    suspend fun fetchPokemonEvolutionChain(@Path("id") id: Int): ApiResponse<PokemonEvolutionChain>
}