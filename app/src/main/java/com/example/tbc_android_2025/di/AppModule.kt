package com.example.tbc_android_2025.di

import com.example.tbc_android_2025.data.HttpStrings.BASE_URL
import com.example.tbc_android_2025.data.HttpStrings.HEADER_KEY
import com.example.tbc_android_2025.data.HttpStrings.HEADER_VALUE
import com.example.tbc_android_2025.data.auth.log_in.service.LogInService
import com.example.tbc_android_2025.data.auth.register.service.RegisterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor = loggingInterceptor)
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader(name = HEADER_KEY, value = HEADER_VALUE)
                    .build()
                chain.proceed(request = newRequest)
            }
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideRegisterService(retrofit: Retrofit): RegisterService =
        retrofit.create(RegisterService::class.java)

    @Provides
    @Singleton
    fun provideLoginService(retrofit: Retrofit): LogInService =
        retrofit.create(LogInService::class.java)

}
