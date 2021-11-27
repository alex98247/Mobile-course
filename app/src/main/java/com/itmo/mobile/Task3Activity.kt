package com.itmo.mobile

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.itmo.mobile.di.DaggerAppComponent
import com.itmo.mobile.model.Book
import com.itmo.mobile.presenter.BookPresenter
import com.itmo.mobile.view.BookView
import javax.inject.Inject

class Task3Activity : AppCompatActivity(), BookView {
    @Inject
    lateinit var bookPresenter: BookPresenter

    private lateinit var loadButton: Button
    private lateinit var bookTitle: TextView
    private lateinit var bookDescription: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task3)
        DaggerAppComponent.create().inject(this)
        bookPresenter.attachView(this)

        loadButton = findViewById(R.id.buttonLoad)
        bookTitle = findViewById(R.id.book_title)
        bookDescription = findViewById(R.id.book_description)
        loadButton.setOnClickListener(this::onLoadButtonClick)
    }

    private fun onLoadButtonClick(view: View) {
        bookPresenter.loadBook()
    }

    override fun showToast(message: String?) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun showBook(book: Book) {
        bookTitle.text = book.title
        bookDescription.text = book.description
    }
}