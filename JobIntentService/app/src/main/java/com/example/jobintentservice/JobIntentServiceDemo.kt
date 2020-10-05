package com.example.jobintentservice

import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.util.Log
import androidx.core.app.JobIntentService

class JobIntentServiceDemo : JobIntentService() {

    override fun onCreate() {
        super.onCreate()
        // Job intent service takes care of the wake lock feature -as CPU runs thread
        // when available
        Log.d(TAG, "onCreate")
    }

    fun myEnqueueWork(context : Context, work : Intent) {
        enqueueWork(context,JobIntentServiceDemo :: class.java, 123,work)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }


    // where we do background work
    // Runs on a background thread in case its run as either an intentService or as a JobService
    override fun onHandleWork(intent: Intent) {
        Log.d(TAG, "onHandleWork")

        val input = intent.getStringExtra("inputExtra")

        // simulating work
        for (i in 0..9) {
            Log.d(TAG, "$input - $i ")

            // Have to stop our work after job is stopped
            if (isStopped) return; // if job has been stopped (onStopCurrentWork was called and returned false)

            SystemClock.sleep(1000)
        }

    }

    // If current work has been stopped if its using the job scheduler
    // Under strong memory pressure // is taking too long
    // Boolean - true - continue work later with same intent (default from super class)
    // false - job is cancelled
    override fun onStopCurrentWork(): Boolean {
        Log.d(TAG, "onStopCurrentWork")
        return super.onStopCurrentWork()

    }

    companion object {
        const val TAG = "JobIntentServiceDemo"
    }
}