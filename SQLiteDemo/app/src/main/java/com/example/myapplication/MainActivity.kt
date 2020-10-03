package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {

    var customers = ArrayList<CustomerModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbh = DataBaseHelper(this)

        var insertionSuccess = false

        //updateCustomerView(dbh)
        customers =  dbh.getEveryCustomer()

        val layoutManager = LinearLayoutManager(this)
        val rvAdapter = CustomerListAdapter(this,  customers)
        rv_customerList.setLayoutManager(layoutManager)
        rv_customerList.setAdapter(rvAdapter)

        btn_add.setOnClickListener() {
            try {
                val customer = CustomerModel(
                    et_name.getText().toString(),
                    et_age.getText().toString().toInt(),
                    sw_active.isChecked,
                    1
                )

                insertionSuccess = dbh.addOne(customer)
                customers.add(customer)

                rvAdapter.notifyDataSetChanged()

                Toast.makeText(this, "Insertion Success: $insertionSuccess", Toast.LENGTH_SHORT).show()

            } catch (e: NumberFormatException) {

                val cm: CustomerModel = CustomerModel("error", 0, false, -1)
                insertionSuccess = dbh.addOne(cm)

                Toast.makeText(this, "Insertion Success: $insertionSuccess", Toast.LENGTH_SHORT).show()

            }
            // CREATE a reference to the database helper to add our customer to the database
            //updateCustomerView(dbh)

        }


//        rv_customerList.setOnItemClickListener { parent, view, position, id ->   // For accessing elements of the elements of the view
//            // position - refers to which item was clicked in the list
//            // Parent - adapter, View - view, id
//            val customer =  parent.getItemAtPosition(position) as CustomerModel
//            // this is a generic type of object return for the Customer model - so we change it safely to Customer model
//            // Since parent is the same adapter as we have been passing to the list view - which has type Customer Model
//            val success = dbh.deleteOne(customer)
//            if(success) {
//                updateCustomerView(dbh)
//                Toast.makeText(this, customer.toString(), Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(this, "FAILED TO DELETE", Toast.LENGTH_SHORT).show()
//            }
//        }

//        sw_active.setOnClickListener() {
//            Toast.makeText(this, "Switch", Toast.LENGTH_SHORT).show()
//        }

    }

    private fun updateCustomerView(dbh: DataBaseHelper) {

        val customers : List<CustomerModel> = dbh.getEveryCustomer()


//        val customerModelViewHolders : ArrayList<CustomerModelViewHolder> = ArrayList<CustomerModelViewHolder>()
//        for(customer: CustomerModel in customers) {
//            customerModelViewHolders.add(CustomerModelViewHolder(customer, ))
//        }
        Log.d("CUSTOMERS", customers.toString())

    }
}