package com.sanjivani.shirditaxi

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class FollowUsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.followus)

        // Initialize buttons
        val instagramButton: ImageButton = findViewById(R.id.instagramButton)
        val facebookButton: ImageButton = findViewById(R.id.facebookButton)
        val linkedinButton: ImageButton = findViewById(R.id.linkedinButton)
        val youtubeButton: ImageButton = findViewById(R.id.youtubeButton)
        val emailButton: ImageButton = findViewById(R.id.emailButton)
        val phoneButton: ImageButton = findViewById(R.id.phoneButton)
        val whatsappButton: ImageButton = findViewById(R.id.whatsappButton)
        val websiteButton: Button = findViewById(R.id.websiteButton)

        // Set click listeners for social media buttons
        instagramButton.setOnClickListener {
            openSocialMedia("https://www.instagram.com/shirditaxi/profilecard/?igsh=emFxcjNvbWZqNjdq")
        }

        facebookButton.setOnClickListener {
            openSocialMedia("https://www.facebook.com/profile.php?id=61568221741033&mibextid=ZbWKwL")
        }

        linkedinButton.setOnClickListener {
            openSocialMedia("https://www.linkedin.com/in/shirdi-taxi-90b935336")
        }

        youtubeButton.setOnClickListener {
            openSocialMedia("https://www.youtube.com/@MakarandAmkar-d3c")
        }

        // Set click listener for email button
        emailButton.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:shirditaxi24@gmail.com"))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Inquiry")
            startActivity(Intent.createChooser(emailIntent, "Choose an Email client"))
        }

        // Set click listener for phone button
        phoneButton.setOnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+918888546042"))
            startActivity(dialIntent)
        }

        // Set click listener for WhatsApp button
        whatsappButton.setOnClickListener {
            val whatsappIntent = Intent(Intent.ACTION_VIEW)
            whatsappIntent.data = Uri.parse("https://wa.me/918888546042")
            startActivity(whatsappIntent)
        }

        // Set click listener for website button
        websiteButton.setOnClickListener {
            openSocialMedia("http://shirditaxi.netlify.app")
        }
    }

    // Helper method to open social media URLs
    private fun openSocialMedia(url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
