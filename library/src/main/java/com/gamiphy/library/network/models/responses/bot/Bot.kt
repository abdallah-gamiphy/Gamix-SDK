package com.gamiphy.library.network.models.responses.bot

data class Bot(
    val __v: Int,
    val _id: String,
    val client: String,
    val createdAt: String,
    val currency: String,
    val daily: Daily,
    val exclusive: Any,
    val freeStyleEnabled: Boolean,
    val intro: Intro,
    val language: Language,
    val levels: List<String>,
    val notification: Notification,
    val rewards: List<Any>,
    val signup: Signup,
    val stagingData: StagingData,
    val style: Style,
    val updatedAt: String,
    val userPoints: UserPoints,
    val visitorFlow: Boolean
)