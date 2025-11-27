package com.example.tbc_android_2025.data.commons

import com.example.tbc_android_2025.data.constants.ErrorMessages
import com.example.tbc_android_2025.domain.commons.Resource
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
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
                is IOException -> ErrorMessages.NETWORK_ERROR
                is HttpException -> ErrorMessages.API_ERROR
                is IllegalStateException -> ErrorMessages.APPLICATION_STATE_ERROR
                else -> ErrorMessages.UNKNOWN_ERROR.plus(
                    other = e.message ?: ErrorMessages.NO_DETAILS_AVAILABLE
                )
            }
            emit(value = Resource.Error(errorMessage = errorMessage))
        } finally {
            emit(value = Resource.Loader(isLoading = false))
        }
    }

}
