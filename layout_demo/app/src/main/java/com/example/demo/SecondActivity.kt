package com.example.demo

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "SecondActivity"

class SecondActivity: AppCompatActivity() {

    var editTextData = ""

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_relative_layout)
        Log.e(TAG, "this is from create")

        val submitButton = findViewById<Button>(R.id.button_id)
        var editText = findViewById<EditText>(R.id.EditText)
        var textView = findViewById<TextView>(R.id.submitted_text)

        if(savedInstanceState != null) {
           var text2 = savedInstanceState.getString("text")
            textView.setText("You said :${text2}")
        }

        // For saving submitted data
        submitButton?.setOnClickListener {
            textView.setText("You said: ${editText?.text.toString()}")
        }

        editText?.addTextChangedListener(textWatcher)
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            editTextData = s.toString()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("text", editTextData)
    }

//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//
//        editTextData = savedInstanceState.get("text")
//
//    }

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

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "this is from destroy")
    }

}