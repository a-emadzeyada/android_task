package com.androidtask.data.remote.service

import com.androidtask.data.remote.model.response.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiServices {

    @GET("latest/{value}")
    suspend fun getRates(@Path("value") value: String):BaseResponse
}