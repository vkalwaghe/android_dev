package com.sanjivani.shirditaxi

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 3000 // Splash screen duration in milliseconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Find the splash image view and apply a fade-in animation
        val splashImage: ImageView? = findViewById(R.id.splashImage) // Check for null

        if (splashImage == null) {
            Toast.makeText(this, "Splash image not found!", Toast.LENGTH_SHORT).show()
            return
        }

        val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        splashImage.startAnimation(fadeInAnimation)

        // Use Handler to delay the transition to LoginActivity
        Handler(Looper.getMainLooper()).postDelayed({
            // Start LoginActivity after the splash timeout
            startActivity(Intent(this, LoginActivity::class.java))
            finish() // Finish the splash activity so it is not in the back stack
        }, SPLASH_TIME_OUT)
    }
}
