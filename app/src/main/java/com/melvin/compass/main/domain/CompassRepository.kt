package com.melvin.compass.main.domain

import okhttp3.ResponseBody

interface CompassRepository {

    suspend fun getCompassContent(): ResponseBody?
}