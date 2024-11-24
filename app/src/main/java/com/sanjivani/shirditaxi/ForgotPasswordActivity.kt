package com.sanjivani.shirditaxi

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sanjivani.shirditaxi.R.id.backToLoginTextView

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var resetButton: Button
    private lateinit var backToLoginTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        // Initialize views
        emailEditText = findViewById(R.id.emailEditText)
        resetButton = findViewById(R.id.resetButton)
        backToLoginTextView = findViewById(R.id.backToLoginTextView)

        // Reset Button click listener
        resetButton.setOnClickListener {
            validateAndSendResetLink()
        }

        // Back to Login Button click listener
        backToLoginTextView.setOnClickListener {
            // Navigate back to Login Activity
            onBackPressed() // You can also use Intent if needed
        }
    }

    private fun validateAndSendResetLink() {
        val email = emailEditText.text.toString()

        // Validation
        if (email.isEmpty()) {
            showToast("Please enter your email address")
            return
        }

        if (!email.contains("@")) {
            showToast("Please enter a valid email address")
            return
        }

        // Simulate sending password reset email (replace with actual logic)
        sendPasswordResetLink(email)
    }

    private fun sendPasswordResetLink(email: String) {
        // Simulate the password reset process (replace with actual logic, like Firebase)
        showToast("A password reset link has been sent to $email")
        finish()  // Close this activity and return to LoginActivity
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
