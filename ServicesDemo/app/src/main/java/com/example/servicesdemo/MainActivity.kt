package com.example.servicesdemo

import android.app.ActivityManager
import android.app.Notification
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(ForegroundServiceDemo.HI, "Hello from main activity's onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val serviceClass = ForegroundServiceDemo :: class.java
        val serviceIntent = Intent(this, serviceClass)


        btn_start_service.setOnClickListener() {
            val input = et_input.getText().toString()
            serviceIntent.putExtra(INPUT_EXTRA, input)

            startService(serviceIntent)

            // can use startForegroundService to promote a service to a foreground service
            // time window of 5s to call startForeground IN service class

        }

        btn_stop_service.setOnClickListener() {
            val serviceIntent = Intent(this, serviceClass)

            stopService(serviceIntent)
        }

//        btn_send_on_channel2.setOnClickListener() {
//            val notification = NotificationCompat.Builder(this, App.CHANNEL_2_ID)
//                .setSmallIcon(R.drawable.ic_two)
//                .setContentTitle(et_title.getText().toString())
//                .setContentText(et_message.getText().toString())
//                .setPriority(NotificationCompat.PRIORITY_LOW)
//                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
//                .build()
//            // for API level lower than oreo for higher these other methods are deprecated since theyre defined in out App class
//
//            notificationManager?.notify(2,notification)
//        }



    }

    override fun onResume() {
        super.onRestart()
        Log.d(ForegroundServiceDemo.HI, "Hello from main activity's onResume")
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d(ForegroundServiceDemo.HI, "Hello from main activity's onDestroy")
    }


    // custom method to determine if a service is running or not
    private fun isServiceRunning(serviceClass: Class<ForegroundServiceDemo>): Boolean {

        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        // Loop through all the running services to check if the services given is actually running or not
        for(service in activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if(service.service.className == serviceClass.name) {
                return true
            }
        }

        return false
    }

    companion object {
        const val INPUT_EXTRA = "inputExtra"
    }
}