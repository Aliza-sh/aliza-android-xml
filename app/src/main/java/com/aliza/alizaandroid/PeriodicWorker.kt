package com.aliza.alizaandroid

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

class PeriodicWorker(private val context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    lateinit var notificationManager:NotificationManager
    override fun doWork(): Result {
        Log.v("periodicWorker", "Here: periodicWorker")

        return try {
            createNotificationChannel()
            Result.success()
        } catch (ex: Exception) {
            Log.v("periodicWorker", "error: ${ex.message}")
            Result.failure()
        }

    }

    private fun createNotificationChannel(){
        notificationManager = context.getSystemService(Service.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("periodicWorkerNotification", "periodic Worker Notification", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }
        createPresenceNotification()
    }

    private fun createPresenceNotification() {

        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK.or(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        val pendingIntent = PendingIntent.getActivity(context,1,intent, PendingIntent.FLAG_IMMUTABLE)

        val notification = NotificationCompat
            .Builder(context, "periodicWorkerNotification")
            .setSmallIcon(R.drawable.img_logo)
            .setLargeIcon(
                BitmapFactory.decodeResource(context.resources,
                R.drawable.img_logo
            ))
            .setContentTitle("periodic Worker")
            .setContentText("periodic Worker Running")
            .setContentIntent(pendingIntent)
            .build()

        notificationManager.notify(50, notification)

    }

}