package com.example.myapplication

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CustomerListAdapter(private val context: Context, private var customerList: List<CustomerModel>) :
    RecyclerView.Adapter<CustomerListAdapter.MyViewHolder>() {


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val customerName : TextView = view.findViewById(R.id.cv_customerName)
        val customerAge : TextView = view.findViewById(R.id.cv_customerAge)
        val customerStatus : TextView = view.findViewById(R.id.cv_customerStatus)
    }



    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): CustomerListAdapter.MyViewHolder{
        // create a new view
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.customer_row, parent, false)
        return MyViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.itemView.setOnClickListener() {
            val dbh = DataBaseHelper(context)
            dbh.deleteOne(customerList.get(position))
            customerList = dbh.getEveryCustomer()
            notifyItemRemoved(position)
            //refresh the activity page.
//            (context as Activity).finish() // to stop and start activity again
//            context.startActivity(context.intent)
        }

        holder.customerName.setText("Name: ${customerList.get(position).getName()}")
        holder.customerAge.setText("Age: ${customerList.get(position).getAge()}")

        val status : Boolean = customerList.get(position).getStatus()
        if(status) {
            holder.customerStatus.setText("Customer is active")
        }else {
            holder.customerStatus.setText("Customer is not active")
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = customerList.size
}