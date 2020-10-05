package com.example.broadcastreceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast

class BroadCastReceiverDemo : BroadcastReceiver()  {

    override fun onReceive(context: Context?, intent: Intent?) {
        // Intent contains action that was triggered
        // This ordering is important as intent may be null leading to app crash
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent?.action)) {
            Toast.makeText(context,"Boot Completed", Toast.LENGTH_SHORT).show()
        }


        // better to use package name as custom action name
        if ("com.example.CUSTOM_BR_ACTION".equals(intent?.action)) {
            val recievedText = intent?.getStringExtra("com.example.EXTRA_TEXT")
            Toast.makeText(context, recievedText, Toast.LENGTH_SHORT).show()
        }

        // Depricated for API >= 24 cant listen for this broadcast as IMPLICIT but can use Dynamic
        // Reason - Whenever connectivity changes is very frequent all apps
        // listening to this broadcast can restart
        // Instead using JOB Scheduler and listening to network changes through that
        // Second way is to listen to Broadcasts dynamically - NOT IN MANIFEST
        if(ConnectivityManager.CONNECTIVITY_ACTION.equals(intent?.action)) {
            val noConnectivity : Boolean? = intent?.getBooleanExtra(
                ConnectivityManager.EXTRA_NO_CONNECTIVITY,
                false
            )
            if (noConnectivity!!) {
                Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show()
            }
        }


    }

}