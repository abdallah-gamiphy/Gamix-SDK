package com.gamiphy.library.network.models.responses

data class Action<T>(
    val data: T,
    val origin: String,
    val ref: String,
    val type: String
)