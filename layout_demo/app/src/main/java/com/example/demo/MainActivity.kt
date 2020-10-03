package com.example.demo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button_id)
        button?.setOnClickListener() {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        Log.e(TAG, "this is from create")
    }

//
//    override fun onStart() {
//        super.onStart()
//        Log.e(TAG, "this is from start")
//    }
//
//    override fun onResume() {
//        super.onResume()
//        Log.e(TAG, "this is from resume")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        Log.e(TAG, "this is from pause")
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Log.e(TAG, "this is from stop")
//
//    }
//
//    override fun onRestart() {
//        super.onRestart()
//        Log.e(TAG, "this is from restart")
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.e(TAG, "this is from destroy")
//    }

    /** Called when the user touches the button */
//    fun gtRelativeLayout(view: View) {
//        val intent = Intent(this, SecondActivity::class.java)
//        startActivity(intent)
//    }

}

