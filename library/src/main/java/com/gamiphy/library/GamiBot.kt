package com.gamiphy.library

import android.content.Context
import com.gamiphy.library.callback.OnAuthTrigger
import com.gamiphy.library.models.CoreConfig
import com.gamiphy.library.models.GamiphyEnvironment
import com.gamiphy.library.models.User
import com.gamiphy.library.ui.GamiphyWebViewActions

interface GamiBot {
    fun init(context: Context, config: CoreConfig): GamiBot
    fun setEnvironment(env: GamiphyEnvironment)
    fun open(context: Context)
    fun close()
    fun login(user: User)
    fun logout(context: Context)
    fun addOnAuthListener(onAuthTrigger: OnAuthTrigger)
    fun registerGamiphyWebViewActions(gamiphyWebViewActions: GamiphyWebViewActions)
    fun unRegisterGamiphyWebViewActions(gamiphyWebViewActions: GamiphyWebViewActions)

    companion object {
        private var instance: GamiBotImpl? = null

        fun getInstance(): GamiBot {
            if (instance == null) {
                instance = GamiBotImpl()
            }
            return instance!!
        }
    }
}