package com.bertan.listviewexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_item.view.*

class MainActivity : AppCompatActivity() {

    data class Person(val name: String, val age: Long)

    private val people = listOf(
            Person("Amelie Parker", 25),
            Person("Kiera Hall", 78),
            Person("Louis Elliott", 62),
            Person("Isabella Berry", 30),
            Person("Jude Sanderson", 52),
            Person("Leah Wade", 50),
            Person("Chloe Birch", 84),
            Person("Michael Turnbull", 77),
            Person("Kiera Watts", 20),
            Person("Tilly Horton", 40)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView.adapter = PersonAdapter(people)
    }

    inner class PersonAdapter(data: List<Person>) : ArrayAdapter<Person>(this, R.layout.row_item, data) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val person = getItem(position)
            val view = LayoutInflater.from(context).inflate(R.layout.row_item, parent, false)
            view.textViewName.text = person?.name
            view.textViewAge.text = person?.age.toString()
            return view
        }
    }
}
