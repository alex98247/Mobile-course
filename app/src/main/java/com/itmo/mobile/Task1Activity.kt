package com.itmo.mobile

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class Task1Activity : AppCompatActivity() {

    private lateinit var taskList: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task1)
        taskList = findViewById(R.id.task_list)
    }
}