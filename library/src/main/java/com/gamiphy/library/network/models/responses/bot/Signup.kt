package com.gamiphy.library.network.models.responses.bot

data class Signup(
    val points: Int,
    val social_networks: List<String>
)