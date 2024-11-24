package com.sanjivani.shirditaxi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class InformationActivity : AppCompatActivity() {

    private lateinit var tvSelectedDate: TextView
    private lateinit var tvSelectedTime: TextView
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)

        // Initialize views
        tvSelectedDate = findViewById(R.id.tv_selected_date)
        tvSelectedTime = findViewById(R.id.tv_selected_time)
        btnBack = findViewById(R.id.btn_back)

        // Retrieve data from intent
        val selectedDate = intent.getStringExtra("selected_date")
        val selectedTime = intent.getStringExtra("selected_time")

        // Display data
        tvSelectedDate.text = "Selected Date: $selectedDate"
        tvSelectedTime.text = "Selected Time: $selectedTime"

        // Back button logic
        btnBack.setOnClickListener {
            val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
