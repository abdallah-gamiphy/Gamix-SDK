package com.gamiphy.library

import android.content.Context
import androidx.annotation.RestrictTo
import com.gamiphy.library.callback.OnAuthTrigger
import com.gamiphy.library.helper.JavaScriptInterfaceHelper
import com.gamiphy.library.models.CoreConfig
import com.gamiphy.library.models.GamiphyEnvironment
import com.gamiphy.library.models.User
import com.gamiphy.library.ui.GamiphyWebViewActions
import com.gamiphy.library.ui.GamiphyWebViewActivity
import com.gamiphy.library.models.GamiphyData

@RestrictTo(RestrictTo.Scope.LIBRARY)
class GamiBotImpl : GamiBot {
    private val gamiphyData = GamiphyData.getInstance()
    private val gamiphyWebViewActionsList = mutableListOf<GamiphyWebViewActions>()

    override fun init(context: Context, config: CoreConfig): GamiBot {
        gamiphyData.config = config
        open(context)
        return this
    }

    override fun setEnvironment(env: GamiphyEnvironment) {
        gamiphyData.env = env
    }

    override fun open(context: Context) {
        context.startActivity(GamiphyWebViewActivity.newIntent(context))
    }

    override fun login(user: User) {
        gamiphyWebViewActionsList.forEach {
            it.login(user)
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

    override fun addOnAuthListener(onAuthTrigger: OnAuthTrigger) {
        JavaScriptInterfaceHelper.onAuthTriggerListeners.add(onAuthTrigger)
    }

    override fun registerGamiphyWebViewActions(gamiphyWebViewActions: GamiphyWebViewActions) {
        gamiphyWebViewActionsList.add(gamiphyWebViewActions)
    }

    override fun unRegisterGamiphyWebViewActions(gamiphyWebViewActions: GamiphyWebViewActions) {
        gamiphyWebViewActionsList.remove(gamiphyWebViewActions)
    }
}