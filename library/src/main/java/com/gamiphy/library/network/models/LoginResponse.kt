package com.gamiphy.library.network.models

import com.gamiphy.library.models.User

data class LoginResponse(
    val token: String,
    val user: User
)