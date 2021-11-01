package com.itmo.mobile

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.itmo.mobile.model.Icon
import com.itmo.mobile.model.Task1DetailScreenState

class Task1DetailActivity : AppCompatActivity() {

    private lateinit var screenState: Task1DetailScreenState
    private lateinit var icon: ImageView
    private lateinit var natText: TextView
    private lateinit var fibText: TextView
    private lateinit var colText: TextView
    private val screenStateKey = "screenState"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task1_detail_screen)
        natText = findViewById(R.id.natText)
        fibText = findViewById(R.id.fibText)
        colText = findViewById(R.id.colText)
        icon = findViewById(R.id.icon)
        screenState = savedInstanceState?.getParcelable(screenStateKey) ?: Task1DetailScreenState(
            description = "ddd"
        )
        natText.text = screenState.natural.toString()
        fibText.text = screenState.fibonacci.toString()
        colText.text = screenState.collatz.toString()
        icon.setImageResource(screenState.icon.iconId)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(screenStateKey, screenState)
    }

    fun onNatButtonClick(view: View) {
        screenState.incrementNatural()
        natText.text = screenState.natural.toString()
    }

    fun onFibButtonClick(view: View) {
        screenState.incrementFibonacci()
        fibText.text = screenState.fibonacci.toString()
    }

    fun onColButtonClick(view: View) {
        screenState.incrementCollatz()
        colText.text = screenState.collatz.toString()
    }
}