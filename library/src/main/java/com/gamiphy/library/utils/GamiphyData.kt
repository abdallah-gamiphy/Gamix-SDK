package com.gamiphy.library.utils

import com.gamiphy.library.models.User

/**
 * This is as a repo for data shared inside the library.
 */
class GamiphyData private constructor() {
    var botId: String = ""
    var user: User? = null
    var debug: Boolean = false
    var language: String? = "en"

    companion object {
        private var instance: GamiphyData? = null

        fun getInstance(): GamiphyData {
            if (instance == null) {
                instance = GamiphyData()
            }
            return instance!!
        }
    }
}