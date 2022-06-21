package com.example.convin_android_task

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class Adapter(context: Context, text: ArrayList<String>) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var text: ArrayList<String>
    private var context: Context

    // Initializing the Views
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var text: TextView

        init {
            text = view.findViewById(R.id.displayText)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflating the Layout(Instantiates layout file into View object)
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)

        return ViewHolder(view)
    }

    // Binding data to the into specified position
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // TypeCast Object to int type

        holder.text.text = text[position]
    }

    override fun getItemCount(): Int {
        // Returns number of items currently available in Adapter
        return text.size
    }



    // Constructor for initialization
    init {
        this.context = context
        this.text = text
    }
}