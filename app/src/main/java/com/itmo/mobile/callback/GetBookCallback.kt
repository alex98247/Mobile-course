package com.itmo.mobile.callback

import android.os.Handler
import android.os.Looper
import com.google.gson.Gson
import com.itmo.mobile.model.Book
import com.itmo.mobile.view.BookView
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException


class GetBookCallback(private val bookView: BookView) : Callback {

    override fun onFailure(call: Call, e: IOException) {
        runOnUiThread(e.message, bookView::showToast)
    }

    override fun onResponse(call: Call, response: Response) {
        if (!response.isSuccessful) {
            runOnUiThread("Unexpected code ${response.code()}", bookView::showToast)
            return
        }
        val book = Gson().fromJson(response.body()?.string(), Book::class.java)
        runOnUiThread(book, bookView::showBook)
    }

    private fun <T> runOnUiThread(input: T, action: (T) -> Unit) {
        Handler(Looper.getMainLooper()).post {
            action(input)
        }
    }
}