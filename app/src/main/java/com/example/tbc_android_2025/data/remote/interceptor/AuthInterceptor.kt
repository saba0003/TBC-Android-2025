package com.example.tbc_android_2025.data.remote.interceptor

import com.example.tbc_android_2025.domain.data_store.DataStoreKeys
import com.example.tbc_android_2025.domain.data_store.DataStoreManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val dataStoreManager: DataStoreManager) :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        val token = runBlocking(context = Dispatchers.IO) {
            dataStoreManager.getPreference(
                key = DataStoreKeys.TOKEN_KEY,
                defaultValue = EMPTY_STRING
            ).first()
        }

        if (token.isNotBlank())
            requestBuilder.header(name = AUTHORIZATION, value = BEARER.plus(other = token))

        return chain.proceed(request = requestBuilder.build())
    }

    private companion object {
        const val EMPTY_STRING = ""
        const val AUTHORIZATION = "Authorization"
        const val BEARER = "Bearer " // A single whitespace in the end is required!
    }
}
