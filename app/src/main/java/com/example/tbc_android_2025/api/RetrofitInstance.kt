package com.example.tbc_android_2025.api

import com.example.tbc_android_2025.user.UserApi
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
                .addHeader(name = "x-api-key", value = "reqres-free-v1")
                .build()
            chain.proceed(request = newRequest)
        }
        .addInterceptor(interceptor = logging)
        .build()

    val api: UserApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(UserApi::class.java)
    }
}
