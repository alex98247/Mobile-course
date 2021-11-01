package com.itmo.mobile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.itmo.mobile.data.DataSource
import com.itmo.mobile.data.DataSourceImpl

class Task1Activity : AppCompatActivity() {

    private lateinit var taskList: ListView
    private val dataSource: DataSource = DataSourceImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task1)
        taskList = findViewById(R.id.task_list)
        val listItems = dataSource.fetchData()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
        taskList.adapter = adapter
        taskList.setOnItemClickListener { _, _, _, _ ->
            val intent = Intent(this, Task1DetailActivity::class.java)
            startActivity(intent)
        }
    }

    fun onToAstButtonClick(view: View) {
        Log.i("Task1Activity", "To AST button clicked")
    }

    fun onHideListButtonClick(view: View) {
        Log.i("Task1Activity", "Hide list button clicked")
        if (taskList.visibility == ListView.VISIBLE) {
            taskList.visibility = ListView.INVISIBLE
        } else {
            taskList.visibility = ListView.VISIBLE
        }
    }
}