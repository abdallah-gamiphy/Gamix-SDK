package com.gamiphy.library.network

import com.gamiphy.library.models.User
import com.gamiphy.library.network.models.LoginResponse
import com.gamiphy.library.network.models.TrackEventRequest
import com.gamiphy.library.network.models.TrackEventResponse
import com.gamiphy.library.network.models.responses.RedeemRequest
import com.gamiphy.library.network.models.responses.RedeemResponse
import com.gamiphy.library.utils.GamiphyConstants
import retrofit2.Call
import retrofit2.http.*

/**
 * Api services for the Gamiphy bot.
 */
interface GamiphyApiServices {

    @POST("bot-event/" + GamiphyConstants.API_V1 + "bot/{botId}/track")
    fun sendTrack(@Path("botId") botId: String, @Body trackEventRequest: TrackEventRequest): Call<TrackEventResponse>

    @POST("clients/" + GamiphyConstants.API_V1 + "bot/{botId}/user")
    fun loginSDK(@Path("botId") botId: String, @Body user: User): Call<LoginResponse>

    @POST("bots/" + GamiphyConstants.API_V1 + "{botId}/package/{packageId}/redeem")
    fun redeem(@Header("Authorization") tokenHeader: String, @Path("botId") botId: String, @Path("packageId") packageId: String, @Body redeemRequest: RedeemRequest): Call<RedeemResponse>
}
