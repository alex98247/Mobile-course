package com.itmo.mobile

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.itmo.mobile.task.IncreaseTask
import java.util.concurrent.atomic.AtomicBoolean

class Task2Activity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var textView2: TextView
    private lateinit var startContinueButton: Button
    private lateinit var stopButton: Button
    private lateinit var resetButton: Button
    private lateinit var thread: Thread
    private lateinit var task: IncreaseTask
    private lateinit var thread2: Thread
    private lateinit var task2: IncreaseTask
    private lateinit var numberPicker: NumberPicker
    private lateinit var numberPicker2: NumberPicker

    private val numberPickerValues = arrayOf("200", "400", "600", "800", "1000")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task2)
        textView = findViewById(R.id.textView)
        textView2 = findViewById(R.id.textView2)
        numberPicker = findViewById(R.id.picker)
        numberPicker2 = findViewById(R.id.picker2)

        processNumberPicker(numberPicker, 2, this::onValueChange)
        processNumberPicker(numberPicker2, 1, this::onValueChange2)

        startContinueButton = findViewById(R.id.start_continue_button)
        startContinueButton.setOnClickListener(this::onStartContinueButtonClick)

        stopButton = findViewById(R.id.stop_button)
        stopButton.setOnClickListener(this::onStopButtonClick)

        resetButton = findViewById(R.id.reset_button)
        resetButton.setOnClickListener(this::onResetButtonClick)

        task = IncreaseTask(600, textView.text.toString().toLong()) { textView.text = it }
        task2 = IncreaseTask(400, textView2.text.toString().toLong()) { textView2.text = it }
    }

    private fun processNumberPicker(
        numberPicker: NumberPicker,
        indexPeriod: Int,
        onValueChange: (NumberPicker, Int, Int) -> Unit
    ) {
        numberPicker.displayedValues = numberPickerValues
        numberPicker.minValue = 0
        numberPicker.maxValue = numberPickerValues.size - 1
        numberPicker.value = indexPeriod
        numberPicker.setOnValueChangedListener(onValueChange)
    }

    private fun onStartContinueButtonClick(view: View?) {
        task.resume()
        thread = Thread(task)
        task2.resume()
        thread2 = Thread(task2)

        thread2.start()
        thread.start()

        stopButton.isEnabled = true
        startContinueButton.isEnabled = false
    }

    private fun onStopButtonClick(view: View?) {
        task.terminate()
        task2.terminate()
        stopButton.isEnabled = false
        startContinueButton.isEnabled = true
        startContinueButton.text = getText(R.string.continue_str)
    }

    private fun onResetButtonClick(view: View?) {
        task.terminate()
        task2.terminate()
        textView.text = getString(R.string.zero)
        textView2.text = getString(R.string.zero)
        stopButton.isEnabled = false
        startContinueButton.isEnabled = true
        startContinueButton.text = getText(R.string.run)
    }

    override fun onStop() {
        super.onStop()
        thread.interrupt()
        thread2.interrupt()
    }

    private fun onValueChange(picker: NumberPicker, oldVal: Int, newVal: Int) {
        task.updatePeriod(numberPickerValues[newVal].toLong())
    }

    private fun onValueChange2(picker: NumberPicker, oldVal: Int, newVal: Int) {
        task2.updatePeriod(numberPickerValues[newVal].toLong())
    }
}
