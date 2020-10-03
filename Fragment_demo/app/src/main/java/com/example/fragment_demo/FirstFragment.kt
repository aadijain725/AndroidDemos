package com.example.fragment_demo

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

const val INTERFACE_TAG = "Must implement FirstFragmentListener"
class FirstFragment : Fragment() {
    internal var listener  = object: FirstFragmentListener {
        override fun inputFromFirst(information: String) {
        }
    }


    // The info that we want to share, when it changes in this fragment we will communicate using
    // this interface
    interface FirstFragmentListener {
        fun inputFromFirst(information : String) // function where we will share the information
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.firstfragment, container, false)
        val textView = view.findViewById<TextView>(R.id.tvF1)
        val button = view.findViewById<Button>(R.id.buttonFromFrag)

        // When button is clicked by user we want to send this information to activity
        button.setOnClickListener() {
            val editText = view.findViewById<EditText>(R.id.etF1)
            listener.inputFromFirst(editText.getText().toString()) // send information to listener
            // But we need to assign this listener something, rn it doesnt have the right context
            // We do that in onAttach as we know for sure the fragment is connected to the Activity
            // Then we can make our Activity implement the interface and store the data in an instance of
            // the activity
        }

        val getArg = arguments?.getString("Activity")

        Log.d("TAG", "onCreateView: $getArg")

        textView.setText(getArg)

        return view
    }

    // this is the first function called in the lifecycle of a fragment so we know for
    // sure that listener will be an instance of the activity and thus activity can have the data
    override fun onAttach(context: Context) {
        super.onAttach(context)
        require(context is FirstFragmentListener) { // only if the activity implements our interface
           Log.e("INTERFACE_TAG", INTERFACE_TAG)
        }
        listener = context // assigned
        // Now when the interface methods are called - here when button is pressed in onCreateView
        // the data from this fragment will be conveyed to activity
    }

    override fun onDetach() {
        super.onDetach()
        listener = object : FirstFragmentListener {
            override fun inputFromFirst(information: String) {
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("F1", "OnDestroy from F1")
    }

    companion object {
        const val ARG_NAME = "textFromActivity"


        fun newInstance(text: String): FirstFragment {
            val fragment = FirstFragment()

            val bundle = Bundle().apply {
                putString(ARG_NAME, text)
            }

            fragment.arguments = bundle

            return fragment
        }
    }

//By Passing the fragment layout id to the constructor we have done what onCreateView would do
// Its only job is to inflate the layout
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(, container, false)
//    }

}
