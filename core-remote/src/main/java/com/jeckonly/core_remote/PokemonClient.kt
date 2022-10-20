package com.jeckonly.core_remote

import com.jeckonly.core_model.dto.NetworkConstant
import com.jeckonly.core_model.dto.pokemondetail.PokemonDetailDto
import com.jeckonly.core_model.dto.pokemonevolutionchain.PokemonEvolutionChain
import com.jeckonly.core_model.dto.pokemonlistitem.PokemonPageDto
import com.jeckonly.core_model.dto.pokemonlistitem.getIdFromUrl
import com.jeckonly.core_model.dto.pokemonspecies.PokemonSpeciesDto
import com.jeckonly.core_remote.api.PokeService
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 上层连接Repo，下层连接service
 * 这一层把参数封装，让repo更好的调用
 */
@Singleton
class PokemonClient @Inject constructor(
    private val pokeService: PokeService
){
    /**
     * @param page 从第1页开始
     */
    suspend fun fetchPokemonList(page: Int): ApiResponse<PokemonPageDto> {
        return pokeService.fetchPokemonPage(
            limit = NetworkConstant.PAGING_SIZE,
            offset = (page - 1) * NetworkConstant.PAGING_SIZE
        )
    }

    /**
     * 获取详细的宝可梦信息
     */
    suspend fun fetchPokemonDetail(nameOrId: String): ApiResponse<PokemonDetailDto> {
        return pokeService.fetchPokemonDetail(nameOrId)
    }

    /**
     * 获取宝可梦种族信息
     */
    suspend fun fetchPokemonSpecies(nameOrId: String): ApiResponse<PokemonSpeciesDto> {
        return pokeService.fetchPokemonSpecies(nameOrId)
    }

    /**
     * 获取宝可梦进化链信息
     */
    suspend fun fetchPokemonEvolutionChain(evolutionChainUrl: String): ApiResponse<PokemonEvolutionChain> {
        return pokeService.fetchPokemonEvolutionChain(getIdFromUrl(evolutionChainUrl))
    }
}