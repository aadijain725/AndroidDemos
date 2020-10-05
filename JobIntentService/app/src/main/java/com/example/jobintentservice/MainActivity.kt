package com.example.jobintentservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_enqueue_work.setOnClickListener() {
            val input = et_input.getText().toString()
            val intent = Intent(this, JobIntentServiceDemo :: class.java)
            intent.putExtra("inputExtra", input)

            // Cant set starting constraints like for a job scheduler
            // Idea is to mimic the intentService behaviour
            JobIntentServiceDemo().myEnqueueWork(this, intent)
        }

    }

}