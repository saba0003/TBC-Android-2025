package com.example.tbc_android_2025.data

import com.example.tbc_android_2025.data.HttpStrings.AUTHORIZATION
import com.example.tbc_android_2025.data.HttpStrings.BASE_URL
import com.example.tbc_android_2025.data.HttpStrings.BEARER_TOKEN
import com.example.tbc_android_2025.data.HttpStrings.HEADER_KEY
import com.example.tbc_android_2025.data.HttpStrings.HEADER_VALUE
import com.example.tbc_android_2025.data.auth.UserAuthApi
import com.example.tbc_android_2025.data.auth.SessionManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HttpClient {

    private val logging =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader(name = HEADER_KEY, value = HEADER_VALUE)

            SessionManager.authToken?.let {
                newRequest.addHeader(name = AUTHORIZATION, value = String.format(BEARER_TOKEN, it))
            }

            chain.proceed(request = newRequest.build())
        }
        .addInterceptor(interceptor = logging)
        .build()

    val api: UserAuthApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(UserAuthApi::class.java)
    }
}
