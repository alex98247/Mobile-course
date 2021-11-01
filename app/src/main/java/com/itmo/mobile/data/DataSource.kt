package com.itmo.mobile.data

interface DataSource {
    fun fetchData(): List<String>
}