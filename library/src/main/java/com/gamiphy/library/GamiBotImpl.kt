package com.gamiphy.library

import android.content.Context
import androidx.annotation.RestrictTo
import com.gamiphy.library.actions.GamiphyWebViewActions
import com.gamiphy.library.actions.OnAuthTrigger
import com.gamiphy.library.models.CoreConfig
import com.gamiphy.library.models.GamiphyEnvironment
import com.gamiphy.library.models.User
import com.gamiphy.library.ui.GamiphyWebViewActivity
import com.gamiphy.library.utils.GamiphyData

@RestrictTo(RestrictTo.Scope.LIBRARY)
class GamiBotImpl : GamiBot {
    private val gamiphyData = GamiphyData.getInstance()
    private val gamiphyWebViewActionsList = mutableListOf<GamiphyWebViewActions>()
    private val gamiphyOnAuthTriggerListeners = mutableListOf<OnAuthTrigger>()

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

    override fun notifyAuthTrigger(signUp: Boolean) {
        gamiphyOnAuthTriggerListeners.forEach {
            it.onAuthTrigger(signUp)
        }
    }
}