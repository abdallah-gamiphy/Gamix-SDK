package com.gamiphy.library.network.models.responses

data class UserAuthResponse(
    val token: String,
    val user: UserResponse
)