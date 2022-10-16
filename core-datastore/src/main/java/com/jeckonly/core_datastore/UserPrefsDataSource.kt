package com.jeckonly.core_datastore

import androidx.datastore.core.DataStore
import com.jeckonly.core_model.datastore.DownloadState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPrefsDataSource @Inject constructor(
    private val userPreferences: DataStore<UserPreferences>
) {
    fun getDownloadStateFlow(): Flow<DownloadState> {
        return userPreferences.data.map {
            DownloadState.numberToState(it.databaseDownloadState)
        }
    }

    suspend fun getDownloadState(): DownloadState{
        return userPreferences.data.map {
            DownloadState.numberToState(it.databaseDownloadState)
        }.first()
    }

    suspend fun updateDownloadState(newState: DownloadState) {
        userPreferences.updateData { preferences ->
            val currentState = preferences.databaseDownloadState
            preferences.toBuilder().setDatabaseDownloadState(DownloadState.stateToNumber(newState)).build()
        }
    }
}