package com.gamiphy.gamiphysdk

import android.app.Application
import com.gamiphy.library.GamiBot
import com.gamiphy.library.models.CoreConfig
import com.gamiphy.library.models.GamiphyEnvironment


class DemoApplication() : Application() {

    override fun onCreate() {
        super.onCreate()
        GamiBot.getInstance()
            .init(this, config = CoreConfig(app = "5f040d7cdbd59b001804c401"))
            .setEnvironment(GamiphyEnvironment.DEV)
    }
}