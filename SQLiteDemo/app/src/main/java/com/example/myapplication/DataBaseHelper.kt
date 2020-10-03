// Class that handles all Database operations
package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.lang.Exception


private const val CUSTOMER_TABLE = "CUSTOMER_TABLE"
private const val COLUMN_CUSTOMER_NAME = "CUSTOMER_TABLE"
private const val COLUMN_CUSTOMER_AGE = "CUSTOMER_AGE"
private const val COLUMN_ACTIVE_CUSTOMER = "ACTIVE_CUSTOMER"
private const val COLUMN_ID = "ID"


class DataBaseHelper(
    context: Context?
) : SQLiteOpenHelper(context, "customer.db", null, 1) {

    // Called when you try to access the database for the first time
    // Code that generates  a new table
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableStatement : String = "CREATE TABLE $CUSTOMER_TABLE($COLUMN_CUSTOMER_NAME TEXT, " +
                                            "$COLUMN_CUSTOMER_AGE INT, $COLUMN_ACTIVE_CUSTOMER BOOL, " +
                                            "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT)"
        // usually dont use AUTOINCREMENT as it takes extra disc and ram
        // The main purpose of using attribute AUTOINCREMENT is to prevent SQLite to reuse a value that has not been used or a value from the previously deleted row

        db?.execSQL(createTableStatement)
    }

    //This is called if the database version number changes.
    // It prevents users from breaking when you change database design
    // Allows for froward/Backward comparability
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    // Function to add 1 entry to our database
    // adds 1 customer
    fun addOne(customerModel: CustomerModel) : Boolean{
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()
        // Content values stores data in pairs like a hashmap- cv.put(key, value)
        // cv.get(DataType)(key)

        cv.put(COLUMN_CUSTOMER_NAME, customerModel.getName())
        cv.put(COLUMN_CUSTOMER_AGE, customerModel.getAge())
        cv.put(COLUMN_ACTIVE_CUSTOMER, customerModel.getStatus())
        // No ID because its an AutoIncrement id

        val insert: Long = db.insert(CUSTOMER_TABLE, null, cv)
        // +ve val means successful
        // -ve means unsuccessful

        db.close() // to close database after done
        return insert >= 0
    }


    // Method to SELECT all records (pull data) from out database
    fun  getEveryCustomer() : ArrayList<CustomerModel> {
        var customers : ArrayList<CustomerModel> = ArrayList<CustomerModel>()

        val queryString: String = "SELECT * FROM $CUSTOMER_TABLE"

        val db: SQLiteDatabase = this.readableDatabase // since we are just selecting here
        //No bottleneck created since no locking database

        val cursor : Cursor = db.rawQuery(queryString, null) // returns a cursor

        // if true - then there were results - keep looping
        // Loop through results and create a new customer object for each row and insert to customers array
        while(cursor.moveToNext()) { // till there are no elements remaining to do anything
            try {
                val customerName: String = cursor.getString(0)
                val customerAge: Int = cursor.getInt(1)
                val customerIsActive : Boolean = cursor.getInt(2) > 0
                val customerId: Int = cursor.getInt(3) // we have to convert result from int to bool - no bool in SQLite
                val customer =
                    CustomerModel(customerName, customerAge, customerIsActive, customerId)
                customers.add(customer)
            } catch (e: Exception) {
                Log.e("ERROR", e.toString())
            }
        }

        cursor.close()
        db.close()

        return customers
    }

    // Deletes 1 entry if that is found and return true
    fun deleteOne(customer: CustomerModel) : Boolean {
        Log.e("CUSTOMER", "from delete $customer")
        val db = this.writableDatabase
        val success = db.delete(CUSTOMER_TABLE, "$COLUMN_ID = ${customer.getId()}", null) > 0
        db.close()
        return success
    }

}