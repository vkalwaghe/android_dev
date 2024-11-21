package com.sanjivani.shirditaxi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sanjivani.shirditaxi.R
import com.sanjivani.shirditaxi.models.Ride

class RideAdapter(private val rideList: List<Ride>) : RecyclerView.Adapter<RideAdapter.RideViewHolder>() {

    class RideViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        val startLocationTextView: TextView = itemView.findViewById(R.id.startLocationTextView)
        val endLocationTextView: TextView = itemView.findViewById(R.id.endLocationTextView)
        val fareTextView: TextView = itemView.findViewById(R.id.fareTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RideViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ride, parent, false)
        return RideViewHolder(view)
    }

    override fun onBindViewHolder(holder: RideViewHolder, position: Int) {
        val ride = rideList[position]
        holder.dateTextView.text = ride.date
        holder.startLocationTextView.text = "From: ${ride.startLocation}"
        holder.endLocationTextView.text = "To: ${ride.endLocation}"
        holder.fareTextView.text = "Fare: ${ride.fare}"
    }

    override fun getItemCount() = rideList.size
}
