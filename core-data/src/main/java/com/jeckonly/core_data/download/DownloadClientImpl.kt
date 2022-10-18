package com.jeckonly.core_data.download

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.work.*
import com.jeckonly.util.LogUtil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

const val UNIQUE_DOWNLOAD_NAME = "unique_download_name"

@Singleton
class DownloadClientImpl @Inject constructor(
    private val app: Application
): DownloadClient {

    private val workManager = WorkManager.getInstance(app)
    
    private val downloadRequest: OneTimeWorkRequest =
        OneTimeWorkRequestBuilder<DownloadWorker>()
            .build()

    /**
     * 不为null时表示已开启过任务执行
     */
    private var workInfoLiveData: LiveData<(List<WorkInfo>)>? = null

    private val _isRunningDownloadStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)

    override fun downloadDatabase(
        onStart: () -> Unit,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        _isRunningDownloadStateFlow.update {
            true
        }
        workManager.enqueueUniqueWork(
            UNIQUE_DOWNLOAD_NAME,
            ExistingWorkPolicy.KEEP,
            downloadRequest
        )
        val observer = object : Observer<List<WorkInfo>> {
            override fun onChanged(list: List<WorkInfo>) {
                if (list.size == 1) {
                    val workInfo = list[0]
                    when (workInfo.state) {
                        WorkInfo.State.ENQUEUED -> {
                            LogUtil.d("入队，还没执行")
                        }
                        WorkInfo.State.BLOCKED -> {
                            LogUtil.d( "正在阻塞")
                        }
                        WorkInfo.State.RUNNING -> {
                            LogUtil.d( "开始执行")

                            onStart()
                        }
                        WorkInfo.State.SUCCEEDED -> {
                            LogUtil.d( "成功执行完成")

                            onSuccess()
                            workInfoLiveData!!
                                .removeObserver(this)
                            _isRunningDownloadStateFlow.update {
                                false
                            }
                        }
                        WorkInfo.State.FAILED -> {
                            LogUtil.d( "执行失败")

                            onFailure(workInfo.outputData.getString(DownloadWorker.FAIL_REASON) ?: "network error")
                            workInfoLiveData!!
                                .removeObserver(this)
                            _isRunningDownloadStateFlow.update {
                                false
                            }
                        }
                        WorkInfo.State.CANCELLED -> {
                            LogUtil.d( "取消了")

                            onFailure("download is canceled")
                            workInfoLiveData!!
                                .removeObserver(this)
                            _isRunningDownloadStateFlow.update {
                                false
                            }
                        }
                    }
                } else {
                    LogUtil.d( "work info列表长度为${list.size}, 不合法，应该为1")

                    onFailure("internal error")
                    workInfoLiveData!!
                        .removeObserver(this)
                    _isRunningDownloadStateFlow.update {
                        false
                    }
                }
            }
        }
        workInfoLiveData = workManager.getWorkInfosForUniqueWorkLiveData(UNIQUE_DOWNLOAD_NAME).apply {
            observeForever(observer)
        }
    }

    override fun getIsRunningDownloadStateFlow(): StateFlow<Boolean> {
        return _isRunningDownloadStateFlow
    }
}