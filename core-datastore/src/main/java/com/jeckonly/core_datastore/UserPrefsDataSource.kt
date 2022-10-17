package com.jeckonly.core_datastore

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPrefsDataSource @Inject constructor(
    private val userPreferences: DataStore<UserPreferences>
) {
    fun getDownloadStateFlow(): Flow<Boolean> {
        return userPreferences.data.map {
           it.finishDownload
        }
    }

    suspend fun isFinishDownload(): Boolean {
        return userPreferences.data.map {
            it.finishDownload
        }.first()
    }

    suspend fun updateDownloadState(isFinished: Boolean) {
        userPreferences.updateData { preferences ->
            val currentState = preferences.finishDownload
            preferences.toBuilder().setFinishDownload(isFinished).build()
        }
    }
}