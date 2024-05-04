package com.aliza.alizaandroid

import android.app.Application
import android.app.NotificationManager
import com.aliza.alizaandroid.base.SIMPLE_NOTIFICATION
import com.aliza.alizaandroid.base.STYLE_NOTIFICATION

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        NotificationHelper(applicationContext)
            .ChannelBuilder()
            .setChannelId(SIMPLE_NOTIFICATION)
            .setChannelName("simple notification")
            .setChannelDescription("this channel shows simple notifications")
            .setChannelImportance(NotificationManager.IMPORTANCE_HIGH)
            .build()

        NotificationHelper(applicationContext)
            .ChannelBuilder()
            .setChannelId(STYLE_NOTIFICATION)
            .setChannelName("style notification")
            .setChannelDescription("this channel shows style notifications")
            .setChannelImportance(NotificationManager.IMPORTANCE_HIGH)
            .build()

    }


}