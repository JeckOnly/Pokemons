package com.jeckonly.core_data.common.repo.interface_

import com.jeckonly.core_model.domain.ResourceState
import com.jeckonly.core_model.ui.PokemonInfoUI
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.NonCancellable.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.yield

interface PokemonRepo {
    /**
     *
     */
    fun getAllItemLessThanPage(page: Int): Flow<ResourceState<List<PokemonInfoUI>>>
}



