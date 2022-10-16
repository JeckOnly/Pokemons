package com.jeckonly.core_data.common.repo.impl

import com.jeckonly.core_data.common.repo.interface_.UserPrefsRepo
import com.jeckonly.core_datastore.UserPrefsDataSource
import com.jeckonly.core_model.datastore.DownloadState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPrefsRepoImpl @Inject constructor(
    private val dataSource: UserPrefsDataSource
) : UserPrefsRepo {
    override fun getDownloadStateFlow(): Flow<DownloadState> {
        return dataSource.getDownloadStateFlow()
    }

    override suspend fun updateDownloadStateToStart() {
        dataSource.updateDownloadState(DownloadState.StartButNotFinished)
    }

    override suspend fun updateDownloadStateToFinish() {
        dataSource.updateDownloadState(DownloadState.FinishedDownload)
    }

}