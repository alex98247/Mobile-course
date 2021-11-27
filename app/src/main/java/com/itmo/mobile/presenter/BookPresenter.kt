package com.itmo.mobile.presenter

import com.itmo.mobile.callback.GetBookCallback
import com.itmo.mobile.view.BookView
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor

class BookPresenter {
    private var client: OkHttpClient
    private lateinit var view: BookView
    private val url = "http://192.168.1.64:5000/book"

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        client = OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .build()
    }

    fun attachView(view: BookView) {
        this.view = view
    }

    fun loadBook() {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(GetBookCallback(view))
    }
}