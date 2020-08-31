package com.gamiphy.library.utils

import android.util.Log
import com.gamiphy.library.models.CoreConfig
import com.gamiphy.library.models.User
import com.google.gson.Gson

object JavaScriptScripts {

    fun init(config: CoreConfig): String {
        val initConfig = Gson().toJson(config).toString()
        val json = initConfig.substring(1, initConfig.length - 1)
        Log.d("!@#!$", json)
        return ("javascript: window.Gamiphy.init({\n" +
                "$json,\n" +
                "goToAuth: function (event) {" +
                JAVASCRIPT_OBJ + ".isLoggedIn(JSON.stringify(event)); " +
                "}" +
                "})"
                )
    }

    private fun getUser(user: User? = null) = user?.let {
        val userJson = Gson().toJson(user).toString()
        "user: $userJson,\n"
    } ?: let {
        ""
    }

    fun login(user: User): String {
        val userJson = Gson().toJson(user).toString()
        return ("javascript: window.Gamiphy.login('$userJson')")
    }

    fun logout(): String {
        return ("javascript: window.Gamiphy.logout()")
    }

    const val JAVASCRIPT_OBJ = "javascript_obj"
}