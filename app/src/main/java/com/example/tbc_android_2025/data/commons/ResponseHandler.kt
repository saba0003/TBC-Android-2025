package com.example.tbc_android_2025.data.commons

import com.example.tbc_android_2025.domain.commons.Resource
import com.example.tbc_android_2025.commons.Strings
import com.example.tbc_android_2025.domain.commons.ResourceProvider
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResponseHandler @Inject constructor(private val resourceProvider: ResourceProvider) {

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
                is IOException -> resourceProvider.getString(resId = Strings.error_network)
                is HttpException -> resourceProvider.getString(resId = Strings.error_api)
                is IllegalStateException -> resourceProvider.getString(resId = Strings.error_state)
                else -> resourceProvider.getString(resId = Strings.error_unknown)
            }
            emit(value = Resource.Error(errorMessage = errorMessage))
        } finally {
            emit(value = Resource.Loader(isLoading = false))
        }
    }

}
