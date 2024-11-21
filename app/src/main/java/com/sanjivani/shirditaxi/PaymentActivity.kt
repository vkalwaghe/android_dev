package com.sanjivani.shirditaxi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class PaymentActivity : AppCompatActivity() {

    // Declare the ActivityResultLauncher to handle QR scan result
    private val scanQrLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                // Retrieve the scanned QR code result
                val qrResult = result.data?.getStringExtra("QR_RESULT")
                qrResult?.let {
                    Toast.makeText(this, "Scanned QR Code: $it", Toast.LENGTH_LONG).show()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_status)

        // Set up the back button
        val backButton: ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish() // Closes the activity and navigates back
        }

        // Cash payment description
        val cashOption: TextView = findViewById(R.id.cashOption)
        val cashDescription: TextView = findViewById(R.id.cashDescription)

        // Online payment option and button (QR Code)
        val onlineOption: TextView = findViewById(R.id.OnlineOption)
        val onlinePaymentButton: Button = findViewById(R.id.onlinePaymentButton)

        // Handle click on the "Scan QR" button for online payment
        onlinePaymentButton.setOnClickListener {
            val intent = Intent(this, ScanQRActivity::class.java)
            scanQrLauncher.launch(intent)  // Launch the QR scanner activity
        }
    }
}
