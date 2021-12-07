package com.itmo.mobile

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import android.view.Menu
import android.view.MenuItem


class MainActivity : AppCompatActivity() {

    private val scoreKey = "score"
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var scoreTextView: TextView
    private lateinit var preferences: SharedPreferences
    private var score: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        preferences = getPreferences(MODE_PRIVATE)
        drawerLayout = findViewById(R.id.drawer_layout)
        scoreTextView = findViewById(R.id.score)
        score = preferences.getInt(scoreKey, 0)
        scoreTextView.text = score.toString()
        configureNavigationDrawer()
        configureToolbar()
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
                return true
            }
        }
        return true
    }

    private fun configureNavigationDrawer() {
        drawerLayout = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val navView = findViewById<View>(R.id.navigation) as NavigationView
        navView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { menuItem ->
            val itemId = menuItem.itemId

            if(itemId == R.id.task1){
                val intent = Intent(this, Task1Activity::class.java)
                startActivity(intent)
            }
            if(itemId == R.id.task2){
                val intent = Intent(this, Task2Activity::class.java)
                startActivity(intent)
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            if(itemId == R.id.task3){
                val intent = Intent(this, Task3Activity::class.java)
                startActivity(intent)
            }
            drawerLayout.closeDrawer(GravityCompat.START)


            return@OnNavigationItemSelectedListener true
        })
    }

    private fun configureToolbar() {
        val toolbar: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val actionbar: ActionBar? = supportActionBar
        actionbar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        actionbar?.setDisplayHomeAsUpEnabled(true)
    }

}