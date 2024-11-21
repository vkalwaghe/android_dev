package com.sanjivani.shirditaxi.activities

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sanjivani.shirditaxi.R
import com.sanjivani.shirditaxi.adapters.RideAdapter
import com.sanjivani.shirditaxi.models.Ride

class RideHistoryActivity : AppCompatActivity() {

    private lateinit var rideHistoryRecyclerView: RecyclerView
    private lateinit var rideAdapter: RideAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ride_history)

        rideHistoryRecyclerView = findViewById(R.id.rideHistoryRecyclerView)
        rideHistoryRecyclerView.layoutManager = LinearLayoutManager(this)

        // Sample ride history data
        val rides = listOf(
            Ride("1", "2024-11-20", "Location A", "Location B", "₹300"),
            Ride("2", "2024-11-18", "Location C", "Location D", "₹450"),
            Ride("3", "2024-11-15", "Location E", "Location F", "₹200")
        )

        rideAdapter = RideAdapter(rides)
        rideHistoryRecyclerView.adapter = rideAdapter
    }
}
