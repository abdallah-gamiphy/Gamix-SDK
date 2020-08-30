package com.gamiphy.library.models

data class User(
    var firstName: String,
    var lastName: String,
    var email: String,
    var hash: String,
    var avatar: String = ""
)


//TODO use these.
//id?: string,
//email: string,
//firstName?: string,
//lastName?: string,
//avatar?: string,
//language?: Languages,
//country?: string,
//hash: string,