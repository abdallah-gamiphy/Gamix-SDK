package com.gamiphy.library.utils

import com.gamiphy.library.models.User
import com.google.gson.Gson

object JavaScriptScripts {

    fun init(appId: String, user: User? = null): String {
        return ("javascript: window.Gamiphy.init({\n" +
                                 "app: '$appId',\n" +
                                 getUser(user) +
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