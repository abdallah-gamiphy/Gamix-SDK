package com.gamiphy.library.models

data class CoreConfig(
    var app: String,
    var user: User? = null,
    var visible: Boolean? = true,
    var openByDefault: Boolean? = true,
    var barHidden: Boolean? = true
)



