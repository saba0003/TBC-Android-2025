package com.example.tbc_android_2025.data.auth.commons

import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import retrofit2.Response

class ResponseHandler {

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
        } catch (e: IOException) {
            emit(value = Resource.Error(errorMessage = e.message.orEmpty()))
        } catch (e: HttpException) {
            emit(value = Resource.Error(errorMessage = e.message.orEmpty()))
        } catch (e: IllegalStateException) {
            emit(value = Resource.Error(errorMessage = e.message.orEmpty()))
        } catch (e: Throwable) {
            emit(value = Resource.Error(errorMessage = e.message.orEmpty()))
        }
        emit(value = Resource.Loader(isLoading = false))
    }
}
