package com.itmo.mobile.task

import android.os.Handler
import android.os.Looper
import java.util.concurrent.atomic.AtomicBoolean

class IncreaseTask(
    @Volatile private var period: Long,
    var value: Long,
    private val action: (String) -> Unit,
) : Runnable {
    private val running: AtomicBoolean = AtomicBoolean(true)

    fun terminate() {
        running.compareAndSet(true, false)
    }

    fun resume() {
        running.compareAndSet(false, true)
    }

    override fun run() {
        while (running.get()) {
            try {
                value++
                Handler(Looper.getMainLooper()).post { action(value.toString()) }
                Thread.sleep(period)
            } catch (ex: InterruptedException) {
                running.set(false)
            }
        }
    }

    fun updatePeriod(period: Long) {
        this.period = period
    }
}