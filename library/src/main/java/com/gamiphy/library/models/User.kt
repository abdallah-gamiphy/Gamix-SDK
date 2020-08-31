package com.gamiphy.library.models

data class User(
    var id: String? = null,
    var firstName: String,
    var lastName: String,
    var email: String,
    var hash: String,
    var avatar: String? = null,
    var language: String? = null
)