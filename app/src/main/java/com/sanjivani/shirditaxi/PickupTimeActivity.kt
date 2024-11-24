package com.sanjivani.shirditaxi

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class PickTimeActivity : AppCompatActivity() {

    private lateinit var tvSelectDate: TextView
    private lateinit var tvSelectTime: TextView
    private lateinit var btnSelectDate: Button
    private lateinit var btnSelectTime: Button
    private lateinit var btnSave: Button

    private var selectedDate: String? = null
    private var selectedTime: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_time_picker)

        // Initialize views
        tvSelectDate = findViewById(R.id.tv_select_date)
        tvSelectTime = findViewById(R.id.tv_select_time)
        btnSelectDate = findViewById(R.id.btn_select_date)
        btnSelectTime = findViewById(R.id.btn_select_time)
        btnSave = findViewById(R.id.btn_save)

        // Set up date picker
        btnSelectDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                tvSelectDate.text = "Selected Date: $selectedDate"
            }, year, month, day).show()
        }

        // Set up time picker
        btnSelectTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            TimePickerDialog(this, { _, selectedHour, selectedMinute ->
                selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
                tvSelectTime.text = "Selected Time: $selectedTime"
            }, hour, minute, true).show()
        }

        // Save button logic
        btnSave.setOnClickListener {
            if (selectedDate != null && selectedTime != null) {
                val intent = Intent(this, InformationActivity::class.java)
                intent.putExtra("selected_date", selectedDate)
                intent.putExtra("selected_time", selectedTime)
                startActivity(intent)
            } else {
                tvSelectDate.text = "Please select a date."
                tvSelectTime.text = "Please select a time."
            }
        }
    }
}
