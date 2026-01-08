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
    fun provideFetchService(retrofit: Retrofit): FetchService =
        retrofit.create(FetchService::class.java)

    @Provides
    @Singleton
    fun provideRegisterService(retrofit: Retrofit): RegisterService =
        retrofit.create(RegisterService::class.java)

    @Provides
    @Singleton
    fun provideLoginService(retrofit: Retrofit): LoginService =
        retrofit.create(LoginService::class.java)

}
