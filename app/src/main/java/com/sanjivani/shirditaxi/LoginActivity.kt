package com.sanjivani.shirditaxi

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.button.MaterialButton

class LoginActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: MaterialButton
    private lateinit var registerTextView: TextView
    private lateinit var forgotPasswordTextView: TextView
    private lateinit var googleSignInButton: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        // Initialize views
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)
        registerTextView = findViewById(R.id.registerTextView)
        forgotPasswordTextView = findViewById(R.id.forgotPasswordTextView)
        googleSignInButton = findViewById(R.id.googleSignInButton)

        // Login Button click listener
        loginButton.setOnClickListener {
            validateAndLogin()
        }

        // Register Text click listener
        registerTextView.setOnClickListener {
            // Navigate to Registration Activity
            startActivity(Intent(this, RegistrationActivity::class.java))
            finish() // This can be replaced with navigation logic
        }

        // Forgot Password click listener
        forgotPasswordTextView.setOnClickListener {
            // Handle forgot password logic (e.g., open reset password screen)
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }

        // Google Sign-In Button click listener
        googleSignInButton.setOnClickListener {
            // Handle Google Sign-In logic here
            // Example: initiateGoogleSignIn()
        }
    }

    private fun validateAndLogin() {
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        // Validation
        if (email.isEmpty()) {
            showToast("Please enter your email address")
            return
        }

        if (password.isEmpty()) {
            showToast("Please enter your password")
            return
        }

        // Proceed with login logic
        // For example, you can call an API or Firebase authentication here
        showToast("Login successful")
        finish() // Navigate to the home page or dashboard (this can be replaced with actual navigation)

        val intent = Intent(this, BeforeHomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
