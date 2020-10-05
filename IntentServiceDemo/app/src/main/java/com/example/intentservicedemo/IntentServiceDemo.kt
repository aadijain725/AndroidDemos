package com.example.intentservicedemo

import android.app.IntentService
import android.content.Intent
import android.os.Build
import android.os.PowerManager
import android.os.SystemClock
import android.util.Log
import androidx.core.app.NotificationCompat


// Does work on a separate background thread
class IntentServiceDemo : IntentService("IntentServiceDemo") {

    init {
        setIntentRedelivery(true) // Redeliver intent, when returned calls service with original intent
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")

        //Wake lock- stops service when phones is turned off, but if we want the CPU running we
        // can set this:
        //If the user presses the power button, then the screen will be turned off
        // but the CPU will be kept on until all partial wake locks have been released.

        val powerManager = getSystemService(POWER_SERVICE) as PowerManager
        wakelock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "ExampleApp:WakeLock")
        wakelock?.acquire(10*60*1000L /*10 minutes*/) // timeout for wakelock in milliseconds
        // Drains battery
        Log.d(TAG, "Wakelock acquired")

        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notification = NotificationCompat.Builder(this, App.CHANNEL_ID)
                .setContentTitle("ExampleIntentService")
                .setContentText("Running..")
                .setSmallIcon(R.drawable.ic_android)
                .build()

            startForeground(1, notification) // foreground service for oreo onwards
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        wakelock?.release()
        Log.d(TAG, "onDestroy: WakeLock Released")
    }

    override fun onHandleIntent(intent: Intent?) {
        Log.d(TAG, "onHandleIntent")

        val input = intent?.getStringExtra("inputExtra")

        // simulating work 3s
        for (i in 0..3) {
            Log.d(TAG, "$input - $i")
            SystemClock.sleep(1000) // 1 s
        }
        // Doesn't have a return value, instead in constructor we have to declare
        // setIntentRedelivery(true/false) - false NOT_STICKY and REDELIVER_INTENT
    }


    companion object {
        const val TAG = "IntentServiceDemo"
        private var wakelock : PowerManager.WakeLock? = null
    }

}