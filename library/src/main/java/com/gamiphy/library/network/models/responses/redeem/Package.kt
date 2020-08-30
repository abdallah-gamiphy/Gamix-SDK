package com.gamiphy.library.network.models.responses.redeem

data class Package(
    val __v: Int?,
    val _id: String?,
    val bot: String?,
    val client: String?,
    val items: List<Item>?,
    val meritPoints: Int?,
    val reward: String?
)