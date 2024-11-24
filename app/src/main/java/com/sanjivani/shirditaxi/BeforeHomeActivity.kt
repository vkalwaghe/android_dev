package com.sanjivani.shirditaxi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.widget.HorizontalScrollView
import com.sanjivani.shirditaxi.R.id.bookridebutton

class BeforeHomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.beforehome)

        // Static ImageViews
        val imgDriver: ImageView = findViewById(R.id.imgDriver)
        val imgCarDetails: ImageView = findViewById(R.id.imgCarDetails)
        val imgServices: ImageView = findViewById(R.id.imgServices)
        val imgMoreServices: ImageView = findViewById(R.id.imgMoreServices)
        val imgAccount: ImageView = findViewById(R.id.accountIcon)
        val imgRideHistory: ImageView = findViewById(R.id.RideHistoryActivity)
        val searchBar: EditText = findViewById(R.id.searchBar)
        val bookridebutton: Button = findViewById(bookridebutton)

        // Set click listeners for navigation
        imgDriver.setOnClickListener {
            navigateToActivity("PickTimeActivity", PickTimeActivity::class.java)
        }
        bookridebutton.setOnClickListener {
            navigateToActivity("PickTimeActivity", PickTimeActivity::class.java)
        }
        imgDriver.setOnClickListener {
            navigateToActivity("DriverDetailsActivity", DriverDetailsActivity::class.java)
        }
        imgCarDetails.setOnClickListener {
            navigateToActivity("CarActivity", CarActivity::class.java)
        }
        imgServices.setOnClickListener {
            navigateToActivity("Calculator", Calculator::class.java)
        }
        imgMoreServices.setOnClickListener {
            navigateToActivity("ServicesActivity", ServicesActivity::class.java)
        }
        imgRideHistory.setOnClickListener {
            navigateToActivity("RideHistoryActivity", RideHistoryActivity::class.java)
        }
        imgAccount.setOnClickListener {
            navigateToActivity("AccountActivity", AccountActivity::class.java)
        }

        // Handle first HorizontalScrollView
        val firstScrollViewImages = listOf(
            R.id.imgDriver to DriverDetailsActivity::class.java,
            R.id.imgCarDetails to CarActivity::class.java,
            R.id.imgServices to Calculator::class.java,
            R.id.imgMoreServices to ServicesActivity::class.java
        )

        firstScrollViewImages.forEach { (imageViewId, activityClass) ->
            val imageView: ImageView = findViewById(imageViewId)
            imageView.setOnClickListener {
                Log.d("BeforeHomeActivity", "Navigating to ${activityClass.simpleName}")
                startActivity(Intent(this, activityClass))
            }
        }

        // Handle second HorizontalScrollView
        val secondScrollViewImages = listOf(
            R.id.imgRectangle1 to "Rectangle 1 clicked",
            R.id.imgRectangle2 to "Rectangle 2 clicked"
        )

        secondScrollViewImages.forEach { (imageViewId, message) ->
            val imageView: ImageView = findViewById(imageViewId)
            imageView.setOnClickListener {
                Log.d("BeforeHomeActivity", "Image clicked: $message")
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToActivity(logTag: String, activityClass: Class<*>) {
        Log.d("BeforeHomeActivity", "Navigating to $logTag")
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}
