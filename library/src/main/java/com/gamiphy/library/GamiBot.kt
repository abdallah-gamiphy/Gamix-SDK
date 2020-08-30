package com.gamiphy.library

import android.content.Context
import androidx.annotation.RestrictTo
import com.gamiphy.library.actions.GamiphyWebViewActions
import com.gamiphy.library.actions.OnAuthTrigger
import com.gamiphy.library.actions.OnRedeemTrigger
import com.gamiphy.library.actions.OnTaskTrigger
import com.gamiphy.library.models.User
import com.gamiphy.library.network.models.responses.redeem.Redeem

interface GamiBot {
    fun init(context: Context, botId: String, language: String? = "en", user: User? = null): GamiBot
    fun setBotId(botId: String): GamiBot
    fun setDebug(debug: Boolean)

    fun open(context: Context)
    fun close()

    fun login(user: User)
    fun logout(context: Context)

    fun markTaskDone(eventName: String, quantity: Int? = null)
    fun markTaskDoneSdk(eventName: String, email: String, data: Any? = null)
    fun markRedeemDone(packageId: String, pointsToRedeem: Int)

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    fun registerGamiphyWebViewActions(gamiphyWebViewActions: GamiphyWebViewActions): GamiBotImpl

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    fun unRegisterGamiphyWebViewActions(gamiphyWebViewActions: GamiphyWebViewActions): GamiBotImpl

    fun registerGamiphyOnAuthTrigger(onAuthTrigger: OnAuthTrigger): GamiBotImpl
    fun unRegisterGamiphyOnAuthTrigger(onAuthTrigger: OnAuthTrigger): GamiBotImpl
    fun registerGamiphyOnTaskTrigger(onTaskTrigger: OnTaskTrigger): GamiBotImpl
    fun unRegisterGamiphyOnTaskTrigger(onTaskTrigger: OnTaskTrigger): GamiBotImpl
    fun registerGamiphyOnRedeemTrigger(onRedeemTrigger: OnRedeemTrigger): GamiBotImpl
    fun unRegisterGamiphyOnRedeemTrigger(onRedeemTrigger: OnRedeemTrigger): GamiBotImpl
    fun notifyAuthTrigger(signUp: Boolean)
    fun notifyTaskTrigger(actionName: String)
    fun notifyRedeemTrigger(redeem: Redeem?)
    fun loginSDK(context: Context, user: User)

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