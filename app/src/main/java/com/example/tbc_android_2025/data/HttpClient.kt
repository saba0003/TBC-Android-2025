package com.example.tbc_android_2025.data

import android.content.Context
import com.example.tbc_android_2025.data.HttpStrings.AUTHORIZATION
import com.example.tbc_android_2025.data.HttpStrings.BASE_URL
import com.example.tbc_android_2025.data.HttpStrings.BEARER_TOKEN
import com.example.tbc_android_2025.data.HttpStrings.HEADER_KEY
import com.example.tbc_android_2025.data.HttpStrings.HEADER_VALUE
import com.example.tbc_android_2025.data.HttpStrings.LOGIN_ENDPOINT
import com.example.tbc_android_2025.data.HttpStrings.PATH
import com.example.tbc_android_2025.data.auth.FetchService
import com.example.tbc_android_2025.data.auth.UserAuthApi
import com.example.tbc_android_2025.data.auth.SessionManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HttpClient {

    private lateinit var appContext: Context

    private val logging =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val originalRequest = chain.request()
            val requestBuilder =
                originalRequest.newBuilder().addHeader(name = HEADER_KEY, value = HEADER_VALUE)

            if (originalRequest.url.encodedPath == PATH + LOGIN_ENDPOINT) {
                val token = runBlocking { SessionManager.tokenFlow(context = appContext).first() }
                token?.let {
                    requestBuilder.addHeader(
                        name = AUTHORIZATION,
                        value = String.format(format = BEARER_TOKEN, it)
                    )
                }
            }

            val newRequest = requestBuilder.build()
            chain.proceed(request = newRequest)
        }
        .addInterceptor(logging)
        .build()


    val authApi: UserAuthApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(UserAuthApi::class.java)
    }

    val api: FetchService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(FetchService::class.java)
    }

    fun init(context: Context) {
        appContext = context.applicationContext
    }
}
