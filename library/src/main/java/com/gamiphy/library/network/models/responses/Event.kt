package com.gamiphy.library.network.models.responses

data class Event(
    val _id: String,
    val eventName: String,
    val list: List<EventData>
)