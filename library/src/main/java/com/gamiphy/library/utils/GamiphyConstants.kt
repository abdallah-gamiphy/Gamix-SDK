package com.gamiphy.library.utils

import com.gamiphy.library.models.GamiphyEnvironment

object GamiphyConstants {
    val BOT_API = when (GamiphyData.getInstance().env) {
        GamiphyEnvironment.DEV -> "https://sdk.dev.gamiphy.co"
        GamiphyEnvironment.PROD -> "https://sdk.gamiphy.co"
    }

    val BOT_SCRIPT = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <script src='$BOT_API'></script>\n" +
            "</head>\n" +
            "</html>"
}
