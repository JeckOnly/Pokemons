package com.jeckonly.core_model.datastore

sealed interface DownloadState{
    /**
     * 0
     */
    object NotStartYet: DownloadState

    /**
     * 1
     */
    object StartButNotFinished: DownloadState

    /**
     * 2
     */
    object FinishedDownload: DownloadState

    companion object {
        fun numberToState(number: Int): DownloadState {
            return when(number) {
                0 -> {
                    DownloadState.NotStartYet
                }
                1 -> {
                    DownloadState.StartButNotFinished
                }
                2 -> {
                    DownloadState.FinishedDownload
                }
                else -> {
                    DownloadState.NotStartYet
                }
            }
        }

        fun stateToNumber(state: DownloadState): Int {
            return when(state) {
                NotStartYet -> {
                    0
                }
                StartButNotFinished -> {
                    1
                }
                FinishedDownload -> {
                    2
                }
            }
        }
    }

}