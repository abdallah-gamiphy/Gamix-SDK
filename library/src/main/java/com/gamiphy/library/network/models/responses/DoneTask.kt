package com.gamiphy.library.network.models.responses

data class DoneTask(
    val _id: String,
    val doneAt: String,
    val task: String
)