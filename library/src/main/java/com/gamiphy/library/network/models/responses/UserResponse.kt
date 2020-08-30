package com.gamiphy.library.network.models.responses

data class UserResponse(
    val __v: Int,
    val _id: String,
    val bot: String,
    val createdAt: String,
    val doneTasks: List<DoneTask>,
    val email: String,
    val events: List<Event>,
    val exclusiveTasks: List<String>,
    val level: Int,
    val name: String,
    val points: Int,
    val pristine: Boolean,
    val referralLink: String,
    val role: List<String>,
    val updatedAt: String,
    val user: String,
    val userGroup: String
)