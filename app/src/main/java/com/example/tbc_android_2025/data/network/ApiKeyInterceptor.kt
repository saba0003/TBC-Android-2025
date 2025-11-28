package com.example.tbc_android_2025.data.network

import com.example.tbc_android_2025.data.network.NetworkConstants.HEADER_KEY
import com.example.tbc_android_2025.data.network.NetworkConstants.HEADER_VALUE
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader(name = HEADER_KEY, value = HEADER_VALUE)
            .build()
        return chain.proceed(request)
    }
}
