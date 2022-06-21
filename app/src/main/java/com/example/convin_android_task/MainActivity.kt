package com.example.convin_android_task


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet


class MainActivity : AppCompatActivity() {

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var text: ArrayList<String>
    private val sharedPrefFile = "CurrentNumber"
    private val storeNumber = "storeNumber"
    private val storeArray = "storeArray"
    private var number = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        number = sharedPreferences.getInt(storeNumber, 0)
        text = ArrayList()
        text = getArray()
        text.sort()

        // Getting reference of swipeRefreshLayout and recyclerView
        swipeRefreshLayout = findViewById(R.id.swiperefresh)
        recyclerView = findViewById(R.id.recyclerView)

        // Setting the layout as Linear for vertical orientation to have swipe behavior
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = linearLayoutManager

        // Sending reference and data to Adapter

        val adapter = Adapter(this, text)

        // Setting Adapter to RecyclerView
        recyclerView.adapter = adapter

        // SetOnRefreshListener on SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            number++
            text.add(number.toString())
            adapter.notifyItemInserted(adapter.itemCount)
        }
    }

    private fun getArray(): ArrayList<String> {
        val sp = getSharedPreferences(sharedPrefFile, MODE_PRIVATE)

        //NOTE: if shared preference is null, the method return empty Hashset and not null
        val set = sp.getStringSet(storeArray, HashSet())
        return ArrayList(set!!)
    }

    override fun onStop() {
        super.onStop()
        val editor = sharedPreferences.edit()
        editor.putInt(storeNumber, number)
        val set: MutableSet<String> = HashSet()
        set.addAll(text)
        editor.putStringSet(storeArray, set)
        editor.apply()
    }

}