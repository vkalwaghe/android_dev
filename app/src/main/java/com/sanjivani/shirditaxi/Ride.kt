package com.sanjivani.shirditaxi.models

data class Ride(
    val id: String,
    val date: String,
    val startLocation: String,
    val endLocation: String,
    val fare: String
)
