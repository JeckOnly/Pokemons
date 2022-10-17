package com.jeckonly.core_data.common.repo.interface_

import kotlinx.coroutines.flow.*

interface UserPrefsRepo {

    fun getDownloadStateFlow(): Flow<Boolean>

    suspend fun updateDownloadStateToNotFinish()

    suspend fun updateDownloadStateToFinish()
}



