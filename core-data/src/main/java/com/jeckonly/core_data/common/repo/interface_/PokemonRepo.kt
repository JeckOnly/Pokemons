package com.jeckonly.core_data.common.repo.interface_

import com.jeckonly.core_model.domain.ResourceState
import com.jeckonly.core_model.ui.home.PokemonInfoUI
import kotlinx.coroutines.flow.*

interface PokemonRepo {
    /**
     *
     */
    fun getAllItemLessThanPage(page: Int): Flow<ResourceState<List<PokemonInfoUI>>>
}



