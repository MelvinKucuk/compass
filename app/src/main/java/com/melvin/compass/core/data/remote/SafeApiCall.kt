package com.melvin.compass.core.data.remote

import com.melvin.compass.core.data.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import kotlin.coroutines.cancellation.CancellationException

suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): Resource<T> {
    return withContext(Dispatchers.IO) {
        try {
            val response: Response<T> = apiToBeCalled()

            if (response.isSuccessful) {
                response.body()?.let {
                    Resource.Success(data = it)
                } ?: Resource.Error(errorMessage = "Null body")
            } else {
                Resource.Error(errorMessage = "Something went wrong")
            }

        } catch (e: HttpException) {
            Resource.Error(errorMessage = e.message ?: "Something went wrong")
        } catch (e: IOException) {
            Resource.Error("Please check your network connection")
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            Resource.Error(errorMessage = "Something went wrong")
        }
    }
}