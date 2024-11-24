package com.sanjivani.shirditaxi

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.button.MaterialButton

class RegistrationActivity : AppCompatActivity() {

    private lateinit var fullNameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var registerButton: MaterialButton
    private lateinit var loginTextView: TextView
    private lateinit var googleSignInButton: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_activity)

        // Initialize the views
        fullNameEditText = findViewById(R.id.fullNameEditText)
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText)
        registerButton = findViewById(R.id.registerButton)
        loginTextView = findViewById(R.id.loginTextView)
        googleSignInButton = findViewById(R.id.googleSignInButton)

        // Register Button Click Listener
        registerButton.setOnClickListener {
            validateAndRegister()
        }

        // Login Text Click Listener
        loginTextView.setOnClickListener {
            // Navigate to Login Activity
            startActivity(Intent(this, LoginActivity::class.java))
            finish() // This can be adjusted as per navigation logic
        }

        // Google Sign-In Button Click Listener
        googleSignInButton.setOnClickListener {
            // Handle Google Sign-In functionality here
            // Example: initiateGoogleSignIn()
        }
    }

    private fun validateAndRegister() {
        val fullName = fullNameEditText.text.toString()
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()
        val confirmPassword = confirmPasswordEditText.text.toString()

        // Validation
        if (fullName.isEmpty()) {
            showToast("Please enter your full name")
            return
        }

        if (email.isEmpty()) {
            showToast("Please enter your email address")
            return
        }

        if (password.isEmpty()) {
            showToast("Please enter a password")
            return
        }

        if (confirmPassword.isEmpty()) {
            showToast("Please confirm your password")
            return
        }

        if (password != confirmPassword) {
            showToast("Passwords do not match")
            return
        }

        // Proceed with registration logic
        // For example, you can call an API or save data in Firebase here
        showToast("Registration successful")
        finish() // Navigate to the next screen, e.g., home page or login page
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
