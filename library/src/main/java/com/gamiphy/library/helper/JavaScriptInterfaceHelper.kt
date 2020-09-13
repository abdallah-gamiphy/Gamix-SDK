package com.gamiphy.library.helper

import android.webkit.JavascriptInterface
import com.gamiphy.library.callback.OnAuthTrigger

class JavaScriptInterfaceHelper {

    @JavascriptInterface
    fun isLoggedIn(isLogIn: Boolean) {
        authTrigger(isLogIn)
    }

    fun authTrigger(isLogIn: Boolean) {
        onAuthTriggerListeners.forEach {
            it.onAuthTrigger(isLogIn)
        }
    }

    companion object {
        val onAuthTriggerListeners = mutableListOf<OnAuthTrigger>()
    }
}


