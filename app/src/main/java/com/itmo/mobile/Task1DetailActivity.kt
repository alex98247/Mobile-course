package com.itmo.mobile

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.itmo.mobile.data.AppConstants
import com.itmo.mobile.model.Person
import com.itmo.mobile.model.Task1DetailScreenState

class Task1DetailActivity : AppCompatActivity() {

    private lateinit var screenState: Task1DetailScreenState
    private lateinit var icon: ImageView
    private lateinit var natText: TextView
    private lateinit var fibText: TextView
    private lateinit var colText: TextView
    private lateinit var profileTitle: TextView
    private lateinit var profileDescription: TextView
    private val screenStateKey = "screenState"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task1_detail_screen)
        natText = findViewById(R.id.natText)
        fibText = findViewById(R.id.fibText)
        colText = findViewById(R.id.colText)
        profileTitle = findViewById(R.id.profile_title)
        profileDescription = findViewById(R.id.profile_description)
        icon = findViewById(R.id.icon)
        val extras = intent.extras
        val person = extras?.getSerializable(AppConstants.personExtraKey) as Person
        screenState = savedInstanceState?.getParcelable(screenStateKey) ?: Task1DetailScreenState(
            description = person.description,
            title = person.title,
            icon = person.icon
        )
        natText.text = screenState.natural.toString()
        fibText.text = screenState.fibonacci.toString()
        colText.text = screenState.collatz.toString()
        profileTitle.text = screenState.title
        profileDescription.text = screenState.description
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