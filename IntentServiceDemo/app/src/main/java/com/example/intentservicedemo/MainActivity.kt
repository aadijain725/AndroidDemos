package com.example.intentservicedemo

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        btn_start_service.setOnClickListener() {
            val input = et_input.getText().toString()
            Log.d("MainActivity", "et input is : $input")
            val intentService = Intent(this, IntentServiceDemo :: class.java)
            intentService.putExtra("inputExtra", input)

            ContextCompat.startForegroundService(this, intentService) // For lower APIs than oreo runs a regular background service
        }

//        btn_stop_service.setOnClickListener() {
//            stopService(intentService)
//        }

    }
}