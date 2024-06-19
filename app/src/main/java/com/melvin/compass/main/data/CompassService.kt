package com.melvin.compass.main.data

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface CompassService {

    @GET("/")
    suspend fun getCompassContent(): Response<ResponseBody>
}