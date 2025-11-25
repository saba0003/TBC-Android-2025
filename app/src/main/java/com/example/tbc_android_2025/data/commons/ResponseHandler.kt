package com.example.tbc_android_2025.data.commons

import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class ResponseHandler @Inject constructor() {

    fun <T> safeApiCall(apiCall: suspend () -> Response<T>) = flow {
        emit(value = Resource.Loader(isLoading = true))

        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let { emit(value = Resource.Success(data = it)) }
            } else {
                val error = response.errorBody()?.string()
                emit(value = Resource.Error(errorMessage = error.orEmpty()))
            }
        } catch (e: Exception) {
            val errorMessage = when (e) {
                is IOException -> "Network error: Could not connect to the server."
                is HttpException -> "API error: Received an unexpected response code."
                is IllegalStateException -> "Application state error."
                else -> "An unknown error occurred: ${e.message}"
            }
            emit(value = Resource.Error(errorMessage = errorMessage))
        } finally {
            emit(value = Resource.Loader(isLoading = false))
        }
    }
}
