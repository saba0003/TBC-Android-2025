package com.example.tbc_android_2025.data.commons

import com.example.tbc_android_2025.domain.commons.Resource
import com.example.tbc_android_2025.domain.exceptions.AppError
import com.example.tbc_android_2025.domain.exceptions.AppError.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResponseHandler @Inject constructor() {

    // ContextCompat.getString(context, Strings.error_network)
    // ContextCompat.getString(context, Strings.error_api)
    // ContextCompat.getString(context, Strings.error_state)
    // ContextCompat.getString(context, Strings.error_unknown)
    fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Flow<Resource<T>> = flow {
        emit(value = Resource.Loader(isLoading = true))

        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let { emit(value = Resource.Success(data = it)) }
            } else {
                val error = response.errorBody()?.string()
                emit(value = Resource.Error(error = Message(value = error.orEmpty())))
            }
        } catch (e: Exception) {
            val error: AppError = when (e) {
                is IOException -> Network
                is HttpException -> Api
                is IllegalStateException -> State
                else -> Unknown
            }
            emit(value = Resource.Error(error = error))
        } finally {
            emit(value = Resource.Loader(isLoading = false))
        }
    }

}
