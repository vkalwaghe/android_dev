package com.sanjivani.shirditaxi

import android.app.Activity
import android.os.Bundle
import android.widget.ImageButton

class CarActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cars) // Set the XML layout file (cars.xml)

        lateinit var backButton: ImageButton
        backButton = this.findViewById(R.id.backButton)
        backButton.setOnClickListener {
            onBackPressed()
        }
    }
}