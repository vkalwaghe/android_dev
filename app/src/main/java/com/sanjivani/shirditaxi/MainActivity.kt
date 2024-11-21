package com.sanjivani.shirditaxi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This activity does not need any content or actions
        finish() // Close MainActivity as soon as it's opened, so it won't show anything
    }
}
