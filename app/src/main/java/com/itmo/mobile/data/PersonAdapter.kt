package com.itmo.mobile.data

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.itmo.mobile.model.Person
import android.view.LayoutInflater
import android.widget.TextView


class PersonAdapter(context: Context, resource: Int, objects: List<Person>) :
    ArrayAdapter<Person>(context, resource, objects) {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val user: Person? = getItem(position)
        val convertView = LayoutInflater.from(context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        val title = convertView!!.findViewById(android.R.id.text1) as TextView
        convertView.tag = user?.id
        title.text = user?.title
        return convertView
    }
}