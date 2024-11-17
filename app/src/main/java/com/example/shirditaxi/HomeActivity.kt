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

                fareTextView.text = "Estimated Fare: $$estimatedFare"
                timeTextView.text = "Estimated Time: $estimatedTime mins"
            } else {
                Toast.makeText(this, "Unable to find locations", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getGeoPointFromAddress(address: String): GeoPoint? {
        val geoCoder = Geocoder(this, Locale.getDefault())
        val location = geoCoder.getFromLocationName(address, 1)
        return location?.firstOrNull()?.let { GeoPoint(it.latitude, it.longitude) }
    }

    private fun plotLocationOnMap(geoPoint: GeoPoint, label: String) {
        val marker = Marker(mapView)
        marker.position = geoPoint
        marker.title = label
        mapView.overlays.add(marker)
    }

    private fun calculateFare(start: GeoPoint, destination: GeoPoint): Double {
        // Dummy fare calculation logic
        val distance = start.distanceToAsDouble(destination) / 1000 // Convert to km
        return distance * 10 // Assume $10 per km
    }

    private fun calculateTime(start: GeoPoint, destination: GeoPoint): Int {
        // Dummy time calculation logic
        val distance = start.distanceToAsDouble(destination) / 1000 // Convert to km
        return (distance / 40).toInt() // Assume an average speed of 40 km/h
    }
}
