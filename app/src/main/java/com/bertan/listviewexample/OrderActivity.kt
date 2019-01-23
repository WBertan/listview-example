package com.bertan.listviewexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.order_item.view.*

class OrderActivity : AppCompatActivity() {
    data class Item(val description: String, val value: Double)
    data class Order(val items: List<Item>)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateListView(emptyList())
    }

    fun updateListView(orders: List<Order>) {
        runOnUiThread {
            listView.adapter = OrderAdapter(orders)
        }
    }

    inner class OrderAdapter(data: List<Order>) : ArrayAdapter<Order>(this, R.layout.order_item, data) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val order = getItem(position)
            val view = LayoutInflater.from(context).inflate(R.layout.order_item, parent, false)
            view.textViewTotalItems.text = order?.items?.size?.toString()
            view.textViewTotal.text = order?.items?.sumByDouble { it.value }?.toString()
            return view
        }
    }
}
