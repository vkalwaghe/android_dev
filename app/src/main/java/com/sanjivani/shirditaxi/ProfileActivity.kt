package com.sanjivani.shirditaxi

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.content.SharedPreferences
import android.view.View
import android.widget.Button

class ProfileActivity : AppCompatActivity() {

    private val PICK_IMAGE_REQUEST = 1
    private val STORAGE_PERMISSION_REQUEST_CODE = 2

    private lateinit var profileImageView: ImageView
    private lateinit var addProfilePhotoText: TextView
    private lateinit var backButton: ImageButton
    private lateinit var saveProfileButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile) // Ensure this matches your layout file

        // Initialize views
        profileImageView = findViewById(R.id.profileImageView)
        addProfilePhotoText = findViewById(R.id.addProfilePhotoText)
        backButton = findViewById(R.id.backButton)
        saveProfileButton = findViewById(R.id.saveProfileButton)

        // Load the saved profile image if available
        loadProfileImage()

        saveProfileButton.setOnClickListener { it: View? ->
            saveProfileData()
        }
        // Set click listener for adding profile photo
        addProfilePhotoText.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    STORAGE_PERMISSION_REQUEST_CODE
                )
            } else {
                openGallery()
            }
        }

        // Set click listener for the back button
        backButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun saveProfileData() {
        TODO("Not yet implemented")
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE_REQUEST) {
            val imageUri: Uri? = data?.data
            if (imageUri != null) {
                profileImageView.setImageURI(imageUri)
                saveProfileImageUri(imageUri)
                Toast.makeText(this, "Profile photo updated!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No photo selected. Please try again.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveProfileImageUri(uri: Uri) {
        val sharedPreferences: SharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("profileImageUri", uri.toString())
        editor.apply()
    }

    private fun loadProfileImage() {
        val sharedPreferences: SharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE)
        val imageUriString = sharedPreferences.getString("profileImageUri", null)

        if (imageUriString != null) {
            val imageUri = Uri.parse(imageUriString)
            profileImageView.setImageURI(imageUri)
        }
    }

    private fun clearProfileImage() {
        // Clear the saved profile image URI
        val sharedPreferences: SharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("profileImageUri")
        editor.apply()

        // Reset the ImageView to a placeholder or blank state
        profileImageView.setImageResource(R.drawable.account_circle_24) // Replace with your default image resource
        Toast.makeText(this, "Profile photo cleared on logout!", Toast.LENGTH_SHORT).show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == STORAGE_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery()
            } else {
                Toast.makeText(this, "Permission denied to access external storage", Toast.LENGTH_SHORT).show()
            }
        }
    }
}