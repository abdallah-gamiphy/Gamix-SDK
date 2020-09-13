package com.gamiphy.library.constant

import com.gamiphy.library.models.CoreConfig
import com.google.gson.Gson

object JavaScriptScripts {

    fun init(config: CoreConfig): String {
        val initConfig = Gson().toJson(config).toString()
        val json = initConfig.substring(1, initConfig.length - 1)
        return ("javascript: window.Gamiphy.init({\n" +
                "$json,\n" +
                "goToAuth: function (event) {" +
                JAVASCRIPT_OBJ + ".isLoggedIn(JSON.stringify(event)); " +
                "}" +
                "})"
                )
    }

    const val JAVASCRIPT_OBJ = "javascript_obj"
}