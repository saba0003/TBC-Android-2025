package com.example.tbc_android_2025.data.remote.commons

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.tbc_android_2025.domain.commons.Resource.*
import com.example.tbc_android_2025.commons.Strings
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResponseHandler @Inject constructor(@param:ApplicationContext private val context: Context) {

    fun <T> safeApiCall(apiCall: suspend () -> Response<T>) = flow {
        emit(value = Loader(isLoading = true))

        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let { emit(value = Success(data = it)) }
            } else {
                val error = response.errorBody()?.string()
                emit(value = Error(errorMessage = error.orEmpty()))
            }
        } catch (e: Exception) {
            val errorMessage = when (e) {
                is IOException -> ContextCompat.getString(context, Strings.error_network)
                is HttpException -> ContextCompat.getString(context, Strings.error_api)
                is IllegalStateException -> ContextCompat.getString(context, Strings.error_state)
                else -> ContextCompat.getString(context, Strings.error_unknown)
            }
            emit(value = Error(errorMessage = errorMessage))
        } finally {
            emit(value = Loader(isLoading = false))
        }
    }

}
