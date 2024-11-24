package com.sanjivani.shirditaxi

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.sanjivani.shirditaxi.R.id.*

class AccountActivity : AppCompatActivity() {

    private lateinit var profileImageView: ImageView
    private lateinit var imagePickerLauncher: ActivityResultLauncher<Intent>
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        // Initialize views
        val completedRidesIcon: ImageView = findViewById(R.id.completedRidesIcon)
        val myProfileButton: Button = findViewById(R.id.myProfileButton)
        val paymentStatusButton: Button = findViewById(R.id.paymentStatusButton)
        val logoutButton: Button = findViewById(R.id.logoutButton)
        val backButton: ImageButton = findViewById(R.id.backButton)
        val homeIcon: ImageView = findViewById(R.id.homeIcon)
        val bookinginfo: Button = findViewById(R.id.bookinginfo)

        profileImageView = findViewById(R.id.profileImageView)

        // Load saved profile image
        loadProfileImage()

        // Back button logic
        backButton.setOnClickListener { finish() }

        // Navigation buttons logic
        myProfileButton.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        completedRidesIcon.setOnClickListener {
            val intent = Intent(this, RideHistoryActivity::class.java)
            startActivity(intent)
        }

        paymentStatusButton.setOnClickListener {
            startActivity(Intent(this, PaymentActivity::class.java))
        }

        bookinginfo.setOnClickListener {
            startActivity(Intent(this, InformationActivity::class.java))
        }

        logoutButton.setOnClickListener {
            logout()
        }

        homeIcon.setOnClickListener {
            val intent = Intent(this, BeforeHomeActivity::class.java)
            startActivity(intent)
        }

        // Set up image picker launcher
        imagePickerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val imageUri: Uri? = data?.data
                imageUri?.let {
                    profileImageView.setImageURI(it)
                    saveProfileImageUri(it)
                    Toast.makeText(this, "Profile photo updated!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Set up permission launcher for external storage
        requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                openGallery()
            } else {
                Toast.makeText(this, "Permission denied to read external storage", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        imagePickerLauncher.launch(intent)
    }

    private fun saveProfileImageUri(uri: Uri) {
        val sharedPreferences: SharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE)
        sharedPreferences.edit().putString("profileImageUri", uri.toString()).apply()
    }

    private fun loadProfileImage() {
        val sharedPreferences: SharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE)
        val imageUriString = sharedPreferences.getString("profileImageUri", null)
        imageUriString?.let {
            val imageUri = Uri.parse(it)
            profileImageView.setImageURI(imageUri)
        }
    }

    private fun logout() {
        // Clear profile image and other preferences
        val sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()

        profileImageView.setImageResource(R.drawable.account_circle_24) // Placeholder image
        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()

        // Navigate to LoginActivity and clear the back stack
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
