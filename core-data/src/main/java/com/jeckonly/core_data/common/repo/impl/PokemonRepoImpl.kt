package com.jeckonly.core_data.common.repo.impl

import com.jeckonly.core_data.common.repo.interface_.PokemonRepo
import com.jeckonly.core_database.dao.PokemonInfoEntityDao
import com.jeckonly.core_model.domain.ResourceState
import com.jeckonly.core_model.dto.pokemonlistitem.PokemonInfoDto
import com.jeckonly.core_model.dto.pokemonlistitem.PokemonPageDto
import com.jeckonly.core_model.entity.pokemonlistitem.PokemonInfoEntity
import com.jeckonly.core_model.mapper.pokemonlistitem.toPokemonInfoEntity
import com.jeckonly.core_model.mapper.pokemonlistitem.toPokemonInfoUI
import com.jeckonly.core_model.ui.PokemonInfoUI
import com.jeckonly.core_remote.PokemonClient
import com.skydoves.sandwich.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepoImpl @Inject constructor(
    private val dao: PokemonInfoEntityDao,
    private val networkClient: PokemonClient
) : PokemonRepo {

    /**
     * TODO 数据库中有内容就不会从网络中取，但万一数据库内容有缺失呢？需要保证正确性！
     *
     * 获取小于等于[page]页的宝可梦数据
     */
    override fun getAllItemLessThanPage(page: Int): Flow<ResourceState<List<PokemonInfoUI>>> =
        flow<ResourceState<List<PokemonInfoUI>>> {
            emit(ResourceState.Loading(true))
            val tempList = dao.getPokemonListOnePage(page)
            if (tempList.isNotEmpty()) {
                emit(
                    ResourceState.Success(
                        dao.getAllPokemonListLessThanPage(page)
                            .map { pokemonInfoEntity: PokemonInfoEntity ->
                                pokemonInfoEntity.toPokemonInfoUI()
                            })
                )
            } else {
                val response = networkClient.fetchPokemonList(page)
                response.suspendOnSuccess {
                    val data: PokemonPageDto = this.data
                    val pokemonDtoList = data.results
                    // NOTE 本地持久化存储
                    // TODO insert可以返回int值得到插入的行数，若是int为0插入失败又如何？
                    dao.insertPokemonList(pokemonDtoList.map { pokemonInfoDto: PokemonInfoDto ->
                        pokemonInfoDto.toPokemonInfoEntity(page)
                    })
                    emit(
                        ResourceState.Success(
                            dao.getAllPokemonListLessThanPage(page)
                                .map { pokemonInfoEntity: PokemonInfoEntity ->
                                    pokemonInfoEntity.toPokemonInfoUI()
                                })
                    )
                }.suspendOnFailure {
                    emit(ResourceState.Error(message = message()))
                }
            }
            emit(ResourceState.Loading(false))
        }.flowOn(Dispatchers.Default)
}