package com.sanjivani.shirditaxi

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class DriverDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_details)
        Log.d("DriverDetailsActivity", "DriverDetailsActivity started successfully")

        lateinit var backButton: ImageButton
        backButton = this.findViewById(R.id.backButton)
        backButton.setOnClickListener {
            onBackPressed()
        }
        // Call Driver 1
        val callDriverButton1: Button = findViewById(R.id.callDriverButton1)
        callDriverButton1.setOnClickListener {
            makePhoneCall("9673731160")
        }

        // Call Driver 2
        val callDriverButton2: Button = findViewById(R.id.callDriverButton2)
        callDriverButton2.setOnClickListener {
            makePhoneCall("7218123616")
        }

        // Call Driver 3
        val callDriverButton3: Button = findViewById(R.id.callDriverButton3)
        callDriverButton3.setOnClickListener {
            makePhoneCall("9822476494")
        }

        // Call Driver 4
        val callDriverButton4: Button = findViewById(R.id.callDriverButton4)
        callDriverButton4.setOnClickListener {
            makePhoneCall("8265061775")
        }
    }

    private fun makePhoneCall(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        startActivity(intent)
    }
}
