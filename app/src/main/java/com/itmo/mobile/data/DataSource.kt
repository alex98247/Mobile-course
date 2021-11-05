package com.itmo.mobile.data

import com.itmo.mobile.model.Person

interface DataSource {
    fun fetchData(): List<Person>
}