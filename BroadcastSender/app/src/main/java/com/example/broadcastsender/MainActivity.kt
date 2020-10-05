package com.example.broadcastsender

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_brs_send.setOnClickListener() {
            val intent = Intent("com.example.CUSTOM_BR_ACTION")
            intent.putExtra("com.example.EXTRA_TEXT", "BroadcastReceived")
            sendBroadcast(intent)
        }
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