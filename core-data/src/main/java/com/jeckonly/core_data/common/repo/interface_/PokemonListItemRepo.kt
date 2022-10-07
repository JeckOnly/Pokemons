package com.jeckonly.core_data.common.repo.interface_

import com.jeckonly.core_model.domain.ResourceState
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.NonCancellable.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.yield

interface PokemonListItemRepo {
    /**
     * TODO 定义接口
     * NOTE 返回Flow记得flowOn(Dispatchers.IO)
     */
    fun getAllItemLessThanPage(page: Int)
}



