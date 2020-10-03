// Class that represents 1 database object
package com.example.myapplication

import android.view.View
import androidx.recyclerview.widget.RecyclerView
open class CustomerModel(private val name: String,
                    private val age: Int,
                    private val isActive: Boolean,
                    private val id: Int
) {

    open fun getName() : String{
        return this.name
    }

    open fun getAge() : Int{
        return this.age
    }

    open fun getStatus() : Boolean{
        return this.isActive
    }

    open fun getId() : Int{
        return this.id
    }

    override fun toString(): String {
        var s = ""
        if(isActive) {
            s = "is"
        } else {
            s = "is not "
        }

        return "$name (CUSTOMER ID: $id) is $age years old and $s an active customer"
    }



}