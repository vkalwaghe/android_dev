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
import com.sanjivani.shirditaxi.activities.RideHistoryActivity

class AccountPageActivity : AppCompatActivity() {

    private val STORAGE_PERMISSION_REQUEST_CODE = 2
    private lateinit var profileImageView: ImageView
    private lateinit var imagePickerLauncher: ActivityResultLauncher<Intent>
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        // Initialize views
        val completedRidesButton: Button=findViewById(R.id.completedRidesButton)
        val changeProfilePhotoButton: Button = findViewById(R.id.changeProfilePhotoButton)
        val myProfileButton: Button = findViewById(R.id.myProfileButton)
        val paymentStatusButton: Button = findViewById(R.id.paymentStatusButton)
        val logoutButton: Button = findViewById(R.id.logoutButton)
        logoutButton.setOnClickListener {
            Log.d("Logout", "Logout button clicked")
            logout()
        }

        profileImageView = findViewById(R.id.profileImageView)

        // Load saved profile image
        loadProfileImage()

        // Back button logic
        val backButton: ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }

        myProfileButton.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        completedRidesButton.setOnClickListener {
            val intent = Intent(this, RideHistoryActivity::class.java)
            startActivity(intent)
        }

        // Payment Status button logic
        paymentStatusButton.setOnClickListener {
            val intent = Intent(this, PaymentActivity::class.java)
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
                Toast.makeText(this, "Permission denied to read your external storage", Toast.LENGTH_SHORT).show()
            }
        }

        // Change Profile Photo logic
        changeProfilePhotoButton.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            } else {
                openGallery()
            }
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        imagePickerLauncher.launch(intent)
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
        val sharedPreferences: SharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("profileImageUri")
        editor.apply()
        profileImageView.setImageResource(R.drawable.account_circle_24) // Replace with your placeholder
    }

    private fun logout() {
        // Log the logout action for debugging
        Log.d("Logout", "Logout button clicked")

        // Clear all SharedPreferences data
        val sharedPreferences1 = getSharedPreferences("UserProfile", MODE_PRIVATE)
        sharedPreferences1.edit().clear().apply()

        val sharedPreferences2 = getSharedPreferences("OtherPreferences", MODE_PRIVATE)
        sharedPreferences2.edit().clear().apply()

        // Reset the profile image to a placeholder
        profileImageView.setImageResource(R.drawable.account_circle_24) // Replace with your placeholder

        // Show a toast confirming the logout action
        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()

        // Redirect to LoginActivity (clearing the back stack)
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // Clear back stack
        startActivity(intent)

        // Finish the current activity
        finish()
    }
    class LoginActivity {

    }
}
