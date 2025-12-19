package com.example.tbc_android_2025.di.module

import com.example.tbc_android_2025.data.remote.service.FetchService
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
    fun provideMovieService(retrofit: Retrofit): FetchService =
        retrofit.create(FetchService::class.java)

}
