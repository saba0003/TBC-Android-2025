package com.example.tbc_android_2025.data.commons

import com.example.tbc_android_2025.data.constants.ErrorMessages.API_ERROR
import com.example.tbc_android_2025.data.constants.ErrorMessages.APPLICATION_STATE_ERROR
import com.example.tbc_android_2025.data.constants.ErrorMessages.NETWORK_ERROR
import com.example.tbc_android_2025.data.constants.ErrorMessages.NO_DETAILS_AVAILABLE
import com.example.tbc_android_2025.data.constants.ErrorMessages.UNKNOWN_ERROR
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
                is IOException -> NETWORK_ERROR
                is HttpException -> API_ERROR
                is IllegalStateException -> APPLICATION_STATE_ERROR
                else -> UNKNOWN_ERROR.plus(other = e.message ?: NO_DETAILS_AVAILABLE)
            }
            emit(value = Resource.Error(errorMessage = errorMessage))
        } finally {
            emit(value = Resource.Loader(isLoading = false))
        }
    }
}
