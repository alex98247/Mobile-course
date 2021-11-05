package com.itmo.mobile.data

import com.itmo.mobile.model.Icon
import com.itmo.mobile.model.Person

class DataSourceImpl : DataSource {
    private val data = listOf(
        Person(1, "first", "Description 1", Icon.ANDROID),
        Person(2, "second", "Description 2", Icon.ANDROID_BLACK),
        Person(3, "third", "Description 3", Icon.ANDROID_CIRCLE),
        Person(4, "forth", "Description 4", Icon.ANDROID),
        Person(5, "fifth", "Description 5", Icon.ANDROID_BLACK),
        Person(6, "sixth", "Description 6", Icon.ANDROID_CIRCLE)
    )

    override fun fetchData(): List<Person> = data
}