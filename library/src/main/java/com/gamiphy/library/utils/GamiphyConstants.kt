package com.gamiphy.library.utils

import com.gamiphy.library.models.GamiphyEnvironment

object GamiphyConstants {
    val BOT_API = when (GamiphyData.getInstance().env) {
        GamiphyEnvironment.DEV -> "https://sdk.dev.gamiphy.co"
        GamiphyEnvironment.PROD -> "https://sdk.gamiphy.co"
    }
}
