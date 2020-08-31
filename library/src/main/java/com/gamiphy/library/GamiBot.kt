package com.gamiphy.library

import android.content.Context
import androidx.annotation.RestrictTo
import com.gamiphy.library.actions.GamiphyWebViewActions
import com.gamiphy.library.actions.OnAuthTrigger
import com.gamiphy.library.models.CoreConfig
import com.gamiphy.library.models.GamiphyEnvironment
import com.gamiphy.library.models.User

interface GamiBot {
    fun init(context: Context, config: CoreConfig): GamiBot
    fun setEnvironment(env: GamiphyEnvironment)
    fun open(context: Context)
    fun close()
    fun login(user: User)
    fun logout(context: Context)

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    fun registerGamiphyWebViewActions(gamiphyWebViewActions: GamiphyWebViewActions): GamiBotImpl

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    fun unRegisterGamiphyWebViewActions(gamiphyWebViewActions: GamiphyWebViewActions): GamiBotImpl

    fun registerGamiphyOnAuthTrigger(onAuthTrigger: OnAuthTrigger): GamiBotImpl
    fun unRegisterGamiphyOnAuthTrigger(onAuthTrigger: OnAuthTrigger): GamiBotImpl
    fun notifyAuthTrigger(signUp: Boolean)

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