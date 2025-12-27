package com.example.tbc_android_2025.data.remote.common

import com.example.tbc_android_2025.domain.common.Resource
import com.example.tbc_android_2025.domain.error.AppError.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResponseHandler @Inject constructor() {

    fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Flow<Resource<T>> = flow {
        emit(value = Resource.Loader(isLoading = true))
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let { emit(value = Resource.Success(data = it)) }
            } else {
                val error = when (response.code()) {
                    HTTP_UNAUTHORIZED -> Unauthorized
                    HTTP_NOT_FOUND -> NotFound
                    in HTTP_INTERNAL_SERVER_ERROR..HTTP_NETWORK_CONNECT_TIMEOUT_ERROR -> ServiceUnavailable
                    else -> ApiError(message = response.errorBody()?.string())
                }
                emit(value = Resource.Error(value = error))
            }
        } catch (e: Exception) {
            val error = when (e) {
                is IOException -> Network
                is HttpException -> ServiceUnavailable
                is IllegalStateException -> Technical(message = e.message)
                else -> Unknown
            }
            emit(value = Resource.Error(value = error, throwable = e.cause))
        } finally {
            emit(value = Resource.Loader(isLoading = false))
        }
    }

    companion object {
        const val HTTP_UNAUTHORIZED = 401
        const val HTTP_NOT_FOUND = 404
        const val HTTP_INTERNAL_SERVER_ERROR = 500
        const val HTTP_NETWORK_CONNECT_TIMEOUT_ERROR = 599
    }
}
