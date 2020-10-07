package com.example.broadcastreceivers

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /*For implicit broadcasts --
        val filter = IntentFilter("com.example.CUSTOM_BR_ACTION") -- dont want this as our main br...
        //filter.addAction(android.action.BUG_REPORT)
        // to add more than 1 action filter.addAction()
        registerReceiver(br, filter)
        // we will make out custom broadcast in a separate app
    }


// If we only want out BR while app is in foreground
//    override fun onStart() {
//        super.onStart()
//        //filter.addA ction(android.action.BUG_REPORT)
//        // to add more than 1 action filter.addAction()
//        registerReceiver(br, filter)
//    }
//
//    override fun onStop() {
//        super.onStop()
//        unregisterReceiver(br)
//    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(br)
    }
*/
    companion object {
        private val br = BroadCastReceiverDemo()
    }
}