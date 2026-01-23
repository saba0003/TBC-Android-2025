package com.example.tbc_android_2025.di.module

import com.example.tbc_android_2025.data.remote.service.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideOrderFetchService(retrofit: Retrofit): OrderFetchService =
        retrofit.create(OrderFetchService::class.java)

}
