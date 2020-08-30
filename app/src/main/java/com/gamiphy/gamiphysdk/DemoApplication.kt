package com.gamiphy.gamiphysdk

import android.app.Application
import android.util.Log
import com.gamiphy.library.GamiBot
import com.gamiphy.library.actions.OnRedeemTrigger
import com.gamiphy.library.models.User
import com.gamiphy.library.network.models.responses.redeem.Redeem

class DemoApplication() : Application() {

    override fun onCreate() {
        super.onCreate()
        GamiBot.getInstance().init(
            this, "5f040d7cdbd59b001804c401", "en").setDebug(true)
    }
}