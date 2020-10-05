package com.example.intentservicedemo

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build


class App: Application() {


    override fun onCreate() {
        super.onCreate()
        createNotificationChannel();
    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                "exampleIntentService",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            val manager = getSystemService(NotificationManager :: class.java)
            manager.createNotificationChannel(notificationChannel)
        }

    }

    companion object {
        const val  CHANNEL_ID = "ExampleIntentServiceChannel"
        //const val  CHANNEL_2_ID = "channel2"
    }
}