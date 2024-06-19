package com.melvin.compass.main.data

import com.melvin.compass.core.data.Resource
import com.melvin.compass.core.data.remote.safeApiCall
import com.melvin.compass.main.domain.CompassRepository
import okhttp3.ResponseBody
import javax.inject.Inject

class CompassRepositoryImpl @Inject constructor(
    private val compassService: CompassService
) : CompassRepository {

    override suspend fun getCompassContent(): ResponseBody? {
        return when (val response = safeApiCall { compassService.getCompassContent() }) {
            is Resource.Success -> response.data
            is Resource.Error -> null
        }
    }
}