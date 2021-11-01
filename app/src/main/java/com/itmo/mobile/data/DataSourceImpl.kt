package com.itmo.mobile.data

class DataSourceImpl : DataSource {
    private val data = listOf("first", "second", "third", "forth", "fifth", "sixth")

    override fun fetchData(): List<String> = data
}