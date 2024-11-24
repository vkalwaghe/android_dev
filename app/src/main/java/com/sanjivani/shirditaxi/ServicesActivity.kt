package com.sanjivani.shirditaxi

import android.app.Activity
import android.os.Bundle
import android.widget.ImageButton

class ServicesActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.followus) // Link the layout file (services.xml) to this activity

        lateinit var backButton: ImageButton
        backButton = this.findViewById(R.id.backButton)
        backButton.setOnClickListener {
            onBackPressed()
        }
    }
}