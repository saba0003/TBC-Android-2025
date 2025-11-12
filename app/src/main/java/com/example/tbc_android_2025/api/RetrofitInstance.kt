package com.example.tbc_android_2025.api

import com.example.tbc_android_2025.api.ApiStrings.API_BASE_URL
import com.example.tbc_android_2025.api.ApiStrings.API_HEADER_KEY
import com.example.tbc_android_2025.api.ApiStrings.API_HEADER_VALUE
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val logging =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader(name = API_HEADER_KEY, value = API_HEADER_VALUE)
                .build()
            chain.proceed(request = newRequest)
        }
        .addInterceptor(interceptor = logging)
        .build()

    val api: UserApi by lazy {
        Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(UserApi::class.java)
    }
}
