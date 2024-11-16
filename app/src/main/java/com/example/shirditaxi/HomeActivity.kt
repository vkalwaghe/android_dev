package com.example.shirditaxi

import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import org.osmdroid.config.Configuration
import org.osmdroid.util.BoundingBox
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import android.location.Geocoder
import android.widget.ArrayAdapter
import java.util.Locale

class HomeActivity : AppCompatActivity() {

    private lateinit var mapView: MapView
    private lateinit var startLocation: AutoCompleteTextView
    private lateinit var destination: AutoCompleteTextView
    private lateinit var bookRideButton: Button
    private lateinit var fareTextView: TextView
    private lateinit var timeTextView: TextView
    private lateinit var rideOptionSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Initialize Views
        mapView = findViewById(R.id.mapView)
        startLocation = findViewById(R.id.startLocation)
        destination = findViewById(R.id.destination)
        bookRideButton = findViewById(R.id.bookRideButton)
        fareTextView = findViewById(R.id.fareTextView)
        timeTextView = findViewById(R.id.timeTextView)
        rideOptionSpinner = findViewById(R.id.rideOptionSpinner)

        // Initialize map configuration with SharedPreferences
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        Configuration.getInstance().load(applicationContext, sharedPreferences)

        // Initialize MapView
        mapView.setMultiTouchControls(true)
        val mapController = mapView.controller
        mapController.setZoom(15.0)
        mapController.setCenter(GeoPoint(19.7600, 74.4800)) // Coordinates for Shirdi

        // Define scrollable area limits
        val boundingBox = BoundingBox(20.0, 74.5, 19.5, 74.0)
        mapView.setScrollableAreaLimitDouble(boundingBox)

        // Set up ride options spinner
        val rideOptions = arrayOf("Economy", "Premium", "Luxury")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, rideOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        rideOptionSpinner.adapter = adapter

        // Handle Book Ride Button click
        bookRideButton.setOnClickListener {
            val startAddress = startLocation.text.toString().trim()
            val destinationAddress = destination.text.toString().trim()

            if (startAddress.isEmpty() || destinationAddress.isEmpty()) {
                Toast.makeText(this, "Please enter both start and destination locations", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val startGeoPoint = getGeoPointFromAddress(startAddress)
            val destinationGeoPoint = getGeoPointFromAddress(destinationAddress)

            if (startGeoPoint != null && destinationGeoPoint != null) {
                plotLocationOnMap(startGeoPoint, "Start")
                plotLocationOnMap(destinationGeoPoint, "Destination")

                val estimatedFare = calculateFare(startGeoPoint, destinationGeoPoint)
                val estimatedTime = calculateTime(startGeoPoint, destinationGeoPoint)

                fareTextView.text = "Estimated Fare: ₹$estimatedFare"
                timeTextView.text = "Estimated Time: $estimatedTime mins"
            } else {
                Toast.makeText(this, "Unable to determine locations. Please check the addresses.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Convert address to GeoPoint
    private fun getGeoPointFromAddress(address: String): GeoPoint? {
        return try {
            val geocoder = Geocoder(this, Locale.getDefault())
            val addresses = geocoder.getFromLocationName(address, 1)
            if (!addresses.isNullOrEmpty()) {
                val location = addresses[0]
                GeoPoint(location.latitude, location.longitude)
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    // Add a marker to the map for a specific GeoPoint
    private fun plotLocationOnMap(geoPoint: GeoPoint, title: String) {
        val marker = Marker(mapView)
        marker.position = geoPoint
        marker.title = title
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        mapView.overlays.add(marker)
        mapView.invalidate()
    }

    // Example function to calculate fare
    private fun calculateFare(start: GeoPoint, destination: GeoPoint): Double {
        val distance = calculateDistance(start, destination) // Distance in km
        return (distance * 10).coerceAtLeast(50.0) // Example: ₹10 per km, minimum fare ₹50
    }

    // Example function to calculate travel time
    private fun calculateTime(start: GeoPoint, destination: GeoPoint): Int {
        val distance = calculateDistance(start, destination) // Distance in km
        return (distance * 2).toInt().coerceAtLeast(10) // Example: 2 minutes per km, minimum 10 minutes
    }

    // Calculate distance between two GeoPoints
    private fun calculateDistance(start: GeoPoint, destination: GeoPoint): Double {
        val earthRadiusKm = 6371.0

        val dLat = Math.toRadians(destination.latitude - start.latitude)
        val dLon = Math.toRadians(destination.longitude - start.longitude)

        val lat1 = Math.toRadians(start.latitude)
        val lat2 = Math.toRadians(destination.latitude)

        val a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2)
        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))

        return earthRadiusKm * c
    }
}
