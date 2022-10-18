package com.jeckonly.core_data.download

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.jeckonly.core_database.dao.PokemonInfoEntityDao
import com.jeckonly.core_model.mapper.pokemonlistitem.toPokemonInfoEntity
import com.jeckonly.core_remote.PokemonClient
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onSuccess
import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class DownloadWorker @AssistedInject constructor(
    @Assisted val context: Context,
    @Assisted val params: WorkerParameters,
    private val networkClient: PokemonClient,
    private val dao: PokemonInfoEntityDao
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return download()
    }

    private suspend fun download(): Result {
        var page = 1
        // 判断任务最终是成功还是失败
        var flag = true
        // 判断任务是否已执行到尽头（成功或失败）
        var hasFinished = false

        var failReason = ""

        while (!hasFinished) {
            val response = networkClient.fetchPokemonList(page)
            response.suspendOnSuccess {
                val next: String? = this.data.next
                val result = this.data.results
                dao.insertPokemonList(result.map {
                    it.toPokemonInfoEntity(page)
                })
                if (next != null) {
                    page++
                } else {
                    hasFinished = true
                }
            }.suspendOnFailure {
                flag = false
                hasFinished = true
                failReason = this.message()
            }
        }
        return if (flag) Result.success() else Result.failure(
            workDataOf(FAIL_REASON to failReason)
        )
    }

    companion object {
        const val FAIL_REASON = "fail_reason"
    }
}

