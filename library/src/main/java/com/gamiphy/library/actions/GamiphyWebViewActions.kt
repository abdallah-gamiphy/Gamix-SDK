package com.gamiphy.library.actions

import androidx.annotation.RestrictTo
import androidx.annotation.RestrictTo.Scope

@RestrictTo(Scope.LIBRARY)
interface GamiphyWebViewActions {

    fun login()
    fun logout()

    fun close()

    fun refresh()

    fun markTaskDone(eventName: String)

    fun markRedeemDone(rewardId: String)

}