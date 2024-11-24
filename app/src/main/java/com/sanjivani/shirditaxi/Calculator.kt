package com.sanjivani.shirditaxi

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import java.util.*

class Calculator: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        lateinit var backButton: ImageButton
        backButton = this.findViewById(R.id.backButton)
        backButton.setOnClickListener {
            onBackPressed()
        }
        // Get references to the UI elements
        val distanceInput: EditText = findViewById(R.id.distanceInput)
        val rateInput: EditText = findViewById(R.id.rateInput)  // New input for rate per km
        val additionalChargesInput: EditText = findViewById(R.id.additionalChargesInput)
        val calculateButton: Button = findViewById(R.id.calculateButton)
        val resultTextView: TextView = findViewById(R.id.result)

        // Set up the Calculate Button click listener
        calculateButton.setOnClickListener {
            // Get the input values
            val distance = distanceInput.text.toString().toDoubleOrNull()
            val rate = rateInput.text.toString().toDoubleOrNull() // User-entered rate
            val additionalCharges = additionalChargesInput.text.toString().toDoubleOrNull()

            // Check if inputs are valid
            if (distance != null && rate != null && additionalCharges != null) {
                // Calculate the total amount
                val totalAmount = calculateTotalCost(distance, rate, additionalCharges)

                // Format the result in INR currency format
                val formattedAmount = formatCurrency(totalAmount)

                // Display the result
                resultTextView.text = "Total Amount: $formattedAmount"
            } else {
                // Display error message if any input is invalid
                resultTextView.text = "Please enter valid numbers for all fields."
            }

            // Optionally clear the input fields after calculation
            // distanceInput.text.clear()
            // rateInput.text.clear()
            // additionalChargesInput.text.clear()
        }
    }

    // Function to calculate the total cost
    private fun calculateTotalCost(distance: Double, ratePerKm: Double, additionalCharges: Double): Double {
        // Total cost calculation: Distance * Rate per km + Additional charges
        return (distance * ratePerKm) + additionalCharges
    }

    // Function to format the total amount in INR currency format
    private fun formatCurrency(amount: Double): String {
        // Format the amount as currency in INR (Indian Rupee)
        val locale = Locale("en", "IN")  // Locale for India
        val format = NumberFormat.getCurrencyInstance(locale)
        return format.format(amount)
    }
}
