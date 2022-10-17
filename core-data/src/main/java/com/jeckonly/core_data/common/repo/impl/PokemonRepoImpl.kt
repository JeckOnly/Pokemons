package com.jeckonly.core_data.common.repo.impl

import com.jeckonly.core_data.common.repo.interface_.PokemonRepo
import com.jeckonly.core_database.dao.PokemonInfoEntityDao
import com.jeckonly.core_datastore.UserPrefsDataSource
import com.jeckonly.core_model.domain.ResourceState
import com.jeckonly.core_model.dto.pokemondetail.PokemonDetailDto
import com.jeckonly.core_model.dto.pokemonlistitem.PokemonInfoDto
import com.jeckonly.core_model.dto.pokemonlistitem.PokemonPageDto
import com.jeckonly.core_model.entity.pokemonlistitem.PokemonInfoEntity
import com.jeckonly.core_model.mapper.pokemondetail.toPokemonInfoUI
import com.jeckonly.core_model.mapper.pokemonlistitem.toPokemonInfoEntity
import com.jeckonly.core_model.mapper.pokemonlistitem.toPokemonInfoUI
import com.jeckonly.core_model.ui.home.PokemonInfoUI
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
    private val networkClient: PokemonClient,
    private val dataSource: UserPrefsDataSource
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

    /**
     * 根据有无下载好数据库数据，数据来源于网络或数据库
     *
     * 若无下载数据库，从网络中查询，最多只有一个结果
     *
     * 若已下载数据库，需要判断[nameOrId]是纯数字还是字符串，纯数字就从数据库中查询相同id，最多只有一个结果；字符串就从数据库中模糊匹配，可有多个数据。
     *
     */
    override fun getPokemonInfoByNameOrId(nameOrId: String): Flow<ResourceState<List<PokemonInfoUI>>> =
        flow<ResourceState<List<PokemonInfoUI>>> {
            emit(ResourceState.Loading(true))
            if (!dataSource.isFinishDownload()) {
                // 还没有下载完成数据库
                val response = networkClient.fetchPokemonDetail(nameOrId)
                response.suspendOnSuccess {
                    val data: PokemonDetailDto = this.data
                    emit(
                        ResourceState.Success(
                            listOf(data.toPokemonInfoUI())
                        )
                    )
                }.suspendOnFailure {
                    emit(ResourceState.Error(message = message()))
                }
            } else {
                // 数据库已下载完成
                if (nameOrId.isId()) {
                    val response = dao.getPokemonById(nameOrId.toInt())
                    emit(ResourceState.Success(response.map {
                        it.toPokemonInfoUI()
                    }))
                } else {
                    val response = dao.getPokemonByLikeName(nameOrId)
                    emit(ResourceState.Success(response.map {
                        it.toPokemonInfoUI()
                    }))
                }
            }
            emit(ResourceState.Loading(false))
        }.flowOn(Dispatchers.Default)

    private fun String.isId(): Boolean {
        return try {
            this.toInt()
            true
        } catch (e: NumberFormatException) {
            false
        }
    }
}