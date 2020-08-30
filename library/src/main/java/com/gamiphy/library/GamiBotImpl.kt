package com.gamiphy.library

import android.content.Context
import androidx.annotation.RestrictTo
import androidx.core.content.edit
import com.gamiphy.library.actions.GamiphyWebViewActions
import com.gamiphy.library.actions.OnAuthTrigger
import com.gamiphy.library.actions.OnRedeemTrigger
import com.gamiphy.library.actions.OnTaskTrigger
import com.gamiphy.library.models.User
import com.gamiphy.library.network.models.responses.redeem.Redeem
import com.gamiphy.library.ui.GamiphyWebViewActivity
import com.gamiphy.library.utils.GamiphyData

@RestrictTo(RestrictTo.Scope.LIBRARY)
class GamiBotImpl : GamiBot {
    private val gamiphyData = GamiphyData.getInstance()
    private val gamiphyWebViewActionsList = mutableListOf<GamiphyWebViewActions>()
    private val gamiphyOnAuthTriggerListeners = mutableListOf<OnAuthTrigger>()
    private val gamiphyOnTaskTriggerListeners = mutableListOf<OnTaskTrigger>()
    private val gamiphyOnRedeemTriggerListeners = mutableListOf<OnRedeemTrigger>()

    override fun init(context: Context, botId: String, language: String?, user: User?): GamiBot {
        gamiphyData.botId = botId
        gamiphyData.language = language
        gamiphyData.user = user
        open(context)
        return this
    }

    override fun setDebug(debug: Boolean) {
        gamiphyData.debug = debug
    }

    override fun open(context: Context) {
        context.startActivity(GamiphyWebViewActivity.newIntent(context))
    }

    override fun setBotId(botId: String): GamiBotImpl {
        gamiphyData.botId = botId
        return this
    }

    override fun markRedeemDone(packageId: String, pointsToRedeem: Int) {
    }

    override fun markTaskDone(eventName: String, quantity: Int?) {
        gamiphyWebViewActionsList.forEach {
            it.markTaskDone(eventName)
        }
    }

    override fun markTaskDoneSdk(eventName: String, email: String, data: Any?) {
    }

    override fun loginSDK(context: Context, user: User) {
    }


    override fun login(user: User) {
        gamiphyData.user = user
        gamiphyWebViewActionsList.forEach {
            it.login()
        }
    }

    override fun logout(context: Context) {
        gamiphyWebViewActionsList.forEach {
            it.logout()
        }
    }

    override fun close() {
        gamiphyWebViewActionsList.forEach {
            it.close()
        }
    }

    override fun registerGamiphyWebViewActions(gamiphyWebViewActions: GamiphyWebViewActions): GamiBotImpl {
        gamiphyWebViewActionsList.add(gamiphyWebViewActions)
        return this
    }

    override fun unRegisterGamiphyWebViewActions(gamiphyWebViewActions: GamiphyWebViewActions): GamiBotImpl {
        gamiphyWebViewActionsList.remove(gamiphyWebViewActions)
        return this
    }

    override fun registerGamiphyOnAuthTrigger(onAuthTrigger: OnAuthTrigger): GamiBotImpl {
        gamiphyOnAuthTriggerListeners.add(onAuthTrigger)
        return this
    }

    override fun unRegisterGamiphyOnAuthTrigger(onAuthTrigger: OnAuthTrigger): GamiBotImpl {
        gamiphyOnAuthTriggerListeners.remove(onAuthTrigger)
        return this
    }

    override fun registerGamiphyOnTaskTrigger(onTaskTrigger: OnTaskTrigger): GamiBotImpl {
        gamiphyOnTaskTriggerListeners.add(onTaskTrigger)
        return this
    }

    override fun unRegisterGamiphyOnTaskTrigger(onTaskTrigger: OnTaskTrigger): GamiBotImpl {
        gamiphyOnTaskTriggerListeners.remove(onTaskTrigger)
        return this
    }

    override fun registerGamiphyOnRedeemTrigger(onRedeemTrigger: OnRedeemTrigger): GamiBotImpl {
        gamiphyOnRedeemTriggerListeners.add(onRedeemTrigger)
        return this
    }

    override fun unRegisterGamiphyOnRedeemTrigger(onRedeemTrigger: OnRedeemTrigger): GamiBotImpl {
        gamiphyOnRedeemTriggerListeners.remove(onRedeemTrigger)
        return this
    }

    override fun notifyAuthTrigger(signUp: Boolean) {
        gamiphyOnAuthTriggerListeners.forEach {
            it.onAuthTrigger(signUp)
        }
    }

    override fun notifyTaskTrigger(actionName: String) {
        gamiphyOnTaskTriggerListeners.forEach {
            it.onTaskTrigger(actionName)
        }
    }

    override fun notifyRedeemTrigger(redeem: Redeem?) {
        gamiphyOnRedeemTriggerListeners.forEach {
            it.onRedeemTrigger(redeem)
        }
    }

    companion object {
        private const val TOKEN_PREF = "TOKEN_PREF"
        private const val TOKEN_PREF_ID = "TOKEN_PREF_ID"
    }
}