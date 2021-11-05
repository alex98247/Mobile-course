package com.itmo.mobile.model

import java.io.Serializable

data class Person(
    val id: Long,
    val title: String,
    val description: String,
    val icon: Icon
) : Serializable