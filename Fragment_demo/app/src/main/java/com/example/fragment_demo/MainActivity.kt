package com.example.fragment_demo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), FirstFragment.FirstFragmentListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val args = Bundle()
        args.putString("Activity", "Hello from Activity to FirstFragment")

        val newFragment1 = FirstFragment()
        newFragment1.setArguments(args)

        // Replacing current ViewGroup to display firstFragment
        btnF1.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                add(R.id.flFragment1, newFragment1)
                addToBackStack(null)
                commit() // to apply changes
            }
        }




    }

    // Called in FirstFragment to share data with activity
    override fun inputFromFirst(information: String) {
        Log.d("FromActivity", "Data received: $information")
        val bundle = Bundle()
        bundle.putString("textFromFirstFrag", information)
        val secondFragment = SecondFragment()
        secondFragment.setArguments(bundle)
        supportFragmentManager.beginTransaction().apply {
            add(R.id.flFragment2, secondFragment)
            addToBackStack(null)
            commit()
        }
    }

    // Replacing current ViewGroup to display fragment_2
//        btnF2.setOnClickListener(){
//            supportFragmentManager.beginTransaction().apply {
//                replace(R.id.flFragment,fragment_2())
//                addToBackStack(null)
//                stack_count.setText("Current count is " + supportFragmentManager.getBackStackEntryCount())
//                commit() // to apply changes
//            }
//        }
//    override fun onBackPressed() {
//        super.onBackPressed()
//        stack_count.setText("Current count is " + supportFragmentManager.getBackStackEntryCount())
//    }
}