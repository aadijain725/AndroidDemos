package com.example.informationform

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.form_layout)

        var grade = ""
        var section = ""

        val gradeSpinner: Spinner = findViewById(R.id.grade_spinner)
        val gradeAdapter =
            ArrayAdapter.createFromResource(
            // Create adapter using string array and default spinner layout when selected
            this,
            R.array.grade_array,
            R.layout.spinner_item
        ).also {
            adapter ->
            //Specifying layout for - list of items when choices appear
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply adapter to the spinner
        }
        gradeSpinner.setAdapter(gradeAdapter)

        gradeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                grade = parent?.getItemAtPosition(position).toString()
            }
        }


        val sectionSpinner: Spinner = findViewById(R.id.section_spinner)
        var sectionAdapter =
        ArrayAdapter.createFromResource(
            this,
            R.array.section_array,
            R.layout.spinner_item
        ).also{
            adapter->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        sectionSpinner.setAdapter(sectionAdapter)
        sectionSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                section = parent?.getItemAtPosition(position).toString()
            }
        }

        val submitButton : Button = findViewById(R.id.submit)
        var data = object {
            var name = ""
            var number = ""
            var grade = ""
            var section = ""
            var address = """"""
        }

        submitButton.setOnClickListener() {
            data.name = findViewById<EditText>(R.id.name_text).text.toString()
            data.grade = grade
            data.section = section
            data.address = findViewById<EditText>(R.id.name_text).text.toString()
            data.number = findViewById<EditText>(R.id.number_text).text.toString()

            Log.e("name", data.name)
            Log.e("grade", data.grade)
            Log.e("section", data.section)
            Log.e("address", data.address)
            Log.e("number", data.number)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("hello", "From onDestroy")
    }
}