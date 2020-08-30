package com.gamiphy.library.network

import com.gamiphy.library.BuildConfig
import com.gamiphy.library.utils.GamiphyConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A client to build our retrofit and create our api services.
 *
 * @see GamiphyApiServices
 */
object RetrofitClient {
    var gamiphyApiServices: GamiphyApiServices
        private set
    private var retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(GamiphyConstants.GAMIPHY_API_DOMAIN)
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        gamiphyApiServices = retrofit.create(GamiphyApiServices::class.java)
    }

    private fun getOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
            .addInterceptor(ApisHeadersInterceptor())
        return httpClient.build()
    }
}