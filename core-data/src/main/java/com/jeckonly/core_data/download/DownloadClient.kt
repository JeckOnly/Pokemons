package com.jeckonly.core_data.download

import kotlinx.coroutines.flow.StateFlow

interface DownloadClient {
    fun downloadDatabase(
        onStart: () -> Unit,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

    fun getIsRunningDownloadStateFlow(): StateFlow<Boolean>
}