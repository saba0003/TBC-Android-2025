package com.example.tbc_android_2025.di

import com.example.tbc_android_2025.data.auth.log_in.LogInService
import com.example.tbc_android_2025.data.auth.register.RegisterService
import com.example.tbc_android_2025.data.services.FetchService
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
    fun provideFetchService(retrofit: Retrofit): FetchService =
        retrofit.create(FetchService::class.java)

    @Provides
    @Singleton
    fun provideRegisterService(retrofit: Retrofit): RegisterService =
        retrofit.create(RegisterService::class.java)

    @Provides
    @Singleton
    fun provideLogInService(retrofit: Retrofit): LogInService =
        retrofit.create(LogInService::class.java)

}
