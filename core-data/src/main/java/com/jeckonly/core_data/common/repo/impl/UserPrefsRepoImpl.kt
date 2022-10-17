package com.jeckonly.core_data.common.repo.impl

import com.jeckonly.core_data.common.repo.interface_.UserPrefsRepo
import com.jeckonly.core_datastore.UserPrefsDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPrefsRepoImpl @Inject constructor(
    private val dataSource: UserPrefsDataSource
) : UserPrefsRepo {
    override fun getDownloadStateFlow(): Flow<Boolean> {
        return dataSource.getDownloadStateFlow()
    }

    override suspend fun updateDownloadStateToFinish() {
        dataSource.updateDownloadState(true)
    }

    override suspend fun updateDownloadStateToNotFinish() {
        dataSource.updateDownloadState(false)
    }

}