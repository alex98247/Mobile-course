package com.itmo.mobile

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task1DetailScreenState(
    var natural: Int = 1,
    var fibonacci: Int = 0,
    private var fibonacciPrevious: Int = 1,
    var collatz: Int = 300,
    val description: String,
    val icon: String
) : Parcelable {
    fun incrementFibonacci() {
        val fPrevious = fibonacciPrevious
        fibonacci += fibonacciPrevious
        fibonacciPrevious = fPrevious
    }

    fun incrementCollatz() {
        collatz = if (collatz % 2 == 0) collatz / 2 else 3 * collatz + 1
    }

    fun incrementNatural() {
        natural += 1
    }
}