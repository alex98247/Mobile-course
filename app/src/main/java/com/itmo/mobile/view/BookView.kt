package com.itmo.mobile.view

import com.itmo.mobile.model.Book

interface BookView {
    fun showToast(message: String?)
    fun showBook(book: Book)
}