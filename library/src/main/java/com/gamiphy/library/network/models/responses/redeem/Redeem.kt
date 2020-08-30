package com.gamiphy.library.network.models.responses.redeem

data class Redeem(
    val packageId: String?,
    val pointsToRedeem: Int?,
    val value: Double?
)