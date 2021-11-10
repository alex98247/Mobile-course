package com.itmo.mobile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import com.google.android.material.snackbar.Snackbar
import com.itmo.mobile.data.AppConstants
import com.itmo.mobile.data.DataSource
import com.itmo.mobile.data.DataSourceImpl
import com.itmo.mobile.data.PersonAdapter
import com.itmo.mobile.model.Person
import com.itmo.mobile.model.Task1ScreenState

class Task1Activity : AppCompatActivity() {

    private val screenStateKey: String = "screenStateKey"
    private lateinit var taskList: ListView
    private lateinit var universityNameLabel: TextView
    private lateinit var universityNameSetText: EditText
    private lateinit var switchColor: SwitchCompat
    private val dataSource: DataSource = DataSourceImpl()
    private lateinit var screenState: Task1ScreenState
    private lateinit var mCoordinatorLayout: CoordinatorLayout
    private lateinit var peopleList: Map<Long, Person>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task1)
        taskList = findViewById(R.id.task_list)
        switchColor = findViewById(R.id.switchColor)
        universityNameLabel = findViewById(R.id.universityNameLabel)
        universityNameSetText = findViewById(R.id.universityNameSetText)
        mCoordinatorLayout = findViewById(R.id.mCoordinatorLayout)
        peopleList = dataSource.fetchData().map { it.id to it }.toMap()
        screenState = savedInstanceState?.getParcelable(screenStateKey) ?: Task1ScreenState(
            textLabelText = this.getString(R.string.itmo)
        )

        val adapter = PersonAdapter(this, 0, peopleList.values.toList())
        taskList.adapter = adapter
        taskList.setOnItemClickListener(this::onTaskItemClick)
        switchColor.setOnCheckedChangeListener(this::onSwitchColorCheckedChanged)
        universityNameSetText.doOnTextChanged { text, _, _, _ ->
            screenState.textEditText = text.toString()
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        universityNameLabel.text = screenState.textLabelText
        switchColor.isChecked = screenState.isChecked
        universityNameSetText.setText(screenState.textEditText)
        changeListVisibility(screenState.isVisibleList)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(screenStateKey, screenState)
    }

    fun onToastButtonClick(view: View) {
        Log.i("Task1Activity", "TOAST button clicked")
        Toast.makeText(applicationContext, "TOAST example", Toast.LENGTH_SHORT).show()
    }

    fun onHideListButtonClick(view: View) {
        Log.i("Task1Activity", "Hide list button clicked")
        val isVisible = taskList.visibility == ListView.VISIBLE
        changeListVisibility(!isVisible)
        screenState.isVisibleList = !isVisible
    }

    private fun changeListVisibility(isVisible: Boolean) {
        if (isVisible) {
            taskList.visibility = ListView.VISIBLE
        } else {
            taskList.visibility = ListView.INVISIBLE
        }
    }

    private fun onSwitchColorCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
        if (isChecked) {
            switchColor.setTextColor(ContextCompat.getColor(this, R.color.red))
        } else {
            switchColor.setTextColor(ContextCompat.getColor(this, R.color.black))
        }
        screenState.isChecked = isChecked
    }

    private fun onTaskItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        val intent = Intent(this, Task1DetailActivity::class.java)
        intent.putExtra(AppConstants.personExtraKey, peopleList[view.tag])
        startActivity(intent)
    }

    fun onFabButtonClick(view: View) {
        universityNameLabel.text = universityNameSetText.text
        screenState.textLabelText = universityNameLabel.text.toString()
        Snackbar.make(mCoordinatorLayout, "Text changed", Snackbar.LENGTH_LONG).show()

    }
}