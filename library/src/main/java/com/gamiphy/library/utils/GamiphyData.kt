package com.gamiphy.library.utils

import com.gamiphy.library.models.CoreConfig
import com.gamiphy.library.models.GamiphyEnvironment
import com.gamiphy.library.models.User

/**
 * This is as a repo for data shared inside the library.
 */
class GamiphyData private constructor() {
    var config: CoreConfig? = null
    var env: GamiphyEnvironment = GamiphyEnvironment.DEV

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