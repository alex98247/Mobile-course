package com.itmo.mobile

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout

class MainActivity : AppCompatActivity() {

    private val scoreKey = "score"
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var scoreTextView: TextView
    private lateinit var preferences: SharedPreferences
    private var score: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        preferences = getPreferences(androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE)
        drawerLayout = findViewById(R.id.drawer_layout)
        scoreTextView = findViewById(R.id.score)
        score = preferences.getInt(scoreKey, 0)
        scoreTextView.text = score.toString()
    }

    fun clickMenu(view: View) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    fun onTask1Click(view: View) {
        val intent = Intent(this, Task1Activity::class.java)
        startActivity(intent)
    }

    fun onPlusButtonClick(view: View) {
        score++
        saveScore()
        scoreTextView.text = score.toString()
    }

    fun onMinusButtonClick(view: View) {
        score--
        saveScore()
        scoreTextView.text = score.toString()
    }

    private fun saveScore() {
        val editor = preferences.edit()
        editor.putInt(scoreKey, score)
        editor.apply()
    }


}