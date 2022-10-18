package com.jeckonly.core_data.download

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.jeckonly.core_data.R

object DownloadNotificationClient {
    private const val DOWNLOAD_CHANNEL_ID = "download_channel"
    private const val DOWNLOAD_CHANNEL_NAME = "Download Database Channel"
    private const val DOWNLOAD_CHANNEL_DESCRIPTION = "Used for show user download state"
    private const val DOWNLOAD_NOTIFICATION_ID = 1


    fun createNotificationChannel(context: Context) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                DOWNLOAD_CHANNEL_ID,
                DOWNLOAD_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = DOWNLOAD_CHANNEL_DESCRIPTION

            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun showDownloadStateNotification(context: Context, contentText: String) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notification = NotificationCompat.Builder(context, DOWNLOAD_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Download Database")
            .setContentText(contentText)
            .build()

        notificationManager.notify(DOWNLOAD_NOTIFICATION_ID, notification)
    }

}