package com.example.servicesdemo

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build



class App : Application() {

    // Called BEFORE we start our main activity --  once app starts (as seen in manifest too)
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }


    private fun createNotificationChannel() {
        // only available at android oreo or higher
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                Companion.CHANNEL_ID, // id
                "ExampleServiceChannel", // visible to user - should be clear
                NotificationManager.IMPORTANCE_DEFAULT// Importance level
            )
            channel.setDescription("This is Foreground Service")
//
//            val channel2 = NotificationChannel(
//                Companion.CHANNEL_2_ID, // id
//                "Channel2", // visible to user - should be clear
//                NotificationManager.IMPORTANCE_LOW// Importance level
//            )
//            channel2.setDescription("This is Channel2")

            val manager = getSystemService(NotificationManager :: class.java)
            manager.createNotificationChannel(channel)
        }
    }

    companion object {
        const val  CHANNEL_ID = "exampleServiceChannel"
        //const val  CHANNEL_2_ID = "channel2"
    }
}