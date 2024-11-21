package com.sanjivani.shirditaxi.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.sanjivani.shirditaxi.R

class AccountPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        val rideHistoryButton: Button = findViewById(R.id.completedRidesButton)
        rideHistoryButton.setOnClickListener {
            val intent = Intent(this, RideHistoryActivity::class.java)
            startActivity(intent)
        }
    }
}
