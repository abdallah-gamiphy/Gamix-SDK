package com.gamiphy.library.actions

import androidx.annotation.RestrictTo
import androidx.annotation.RestrictTo.Scope
import com.gamiphy.library.models.User

@RestrictTo(Scope.LIBRARY)
interface GamiphyWebViewActions {

    fun login(user: User)

    fun logout()

    fun close()

    fun refresh()
}