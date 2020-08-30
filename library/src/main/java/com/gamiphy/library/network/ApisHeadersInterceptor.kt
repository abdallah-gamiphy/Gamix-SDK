package com.gamiphy.library.network

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * An Interceptor used in Retrofit to handle api headers and token
 */
class ApisHeadersInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        request = request.newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("EventData-Requested-With", "XMLHttpRequest")
            .build()
        return chain.proceed(request)
    }
}