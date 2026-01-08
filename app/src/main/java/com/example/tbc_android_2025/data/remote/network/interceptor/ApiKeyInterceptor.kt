package com.example.tbc_android_2025.data.remote.network.interceptor

import com.example.tbc_android_2025.BuildConfig.API_HEADER_KEY
import com.example.tbc_android_2025.BuildConfig.API_HEADER_VALUE
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader(name = API_HEADER_KEY, value = API_HEADER_VALUE)
            .build()
        return chain.proceed(request = request)
    }

}
