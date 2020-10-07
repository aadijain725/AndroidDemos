package com.example.broadcastsender

import android.content.*
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_brs_send_to_BRD1.setOnClickListener() {
            // Allows us to explicitly trigger other apps reciever
            // Even tho app isn't running at all
            val intent = Intent("com.example.broadcastsender.EXAMPLE_ACTION")
            // if we want to specify triggered name specifically leave intent empty and use cn pr setClassName
            //val cn = ComponentName()// Package name of other app, BroadcastReceiverDemo (br class)
            //intent.setClassName("com.example.broadcastreceivers", "com.example.broadcastreceivers.BroadCastReceiverDemo")
            // can add intent filters to trigger particular events in other receivers

            // Without specifying the exact BR directly : (BRD1)
            // But now we have to add an intent filter to our other broadcast receivers for them to
            // be able to get triggered on this action call
            // intent filter sets exported to true by default, so don't need it manifest of BRD1

            // intent.setPackage("com.example.broadcastreceivers")

            val pm = getPackageManager()
            val brsList : List<ResolveInfo> = pm.queryBroadcastReceivers(intent, 0)
            for(br in brsList) {
                val componentName = ComponentName(br.activityInfo.packageName, br.activityInfo.name)
                intent.setComponent(componentName)
                sendBroadcast(intent)
            }


        }

        btn_brs_send_to_BRD2.setOnClickListener() {

            // SENDING TO SELF

            // this constructor sets the component -- to a new component the one we pass
            val intent = Intent(this, BroadcastReceiverDemo2 :: class.java)
            // so we can achieve similar functionality using -
            // 1. intent.setClass(this, BroadcastReceiverDemo2 :: class.java)
            // 2. cn = ComponentName(this, BroadcastReceiverDemo2 :: class.java)
            // using 2 we can trigger BR's not in our current app
            sendBroadcast(intent)
        }

//        btn_brs_send_customAction.setOnClickListener() {
//            val intent = Intent("com.example.CUSTOM_BR_ACTION")
//            intent.putExtra("com.example.EXTRA_TEXT", "BroadcastReceived")
//            sendBroadcast(intent)
//        }
    }

    // Better custom receiver for security reasons as - other apps wont be able to receive message
    // Good way to receive data to be able to update UI of activity
    // Can also be used to listen to system broadcasts
    private val broadcastReceiver : BroadcastReceiver = object : BroadcastReceiver () {
        override fun onReceive(context: Context?, intent: Intent?) {
            val receivedText = intent?.getStringExtra("com.example.EXTRA_TEXT")
            tv_brs_input.setText(receivedText)
        }
    }

    override fun onStart() {
        super.onStart()
        val filter = IntentFilter("com.example.CUSTOM_BR_ACTION")
        registerReceiver(broadcastReceiver, filter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadcastReceiver)
    }

}