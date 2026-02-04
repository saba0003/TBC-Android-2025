package com.example.register.di.module

import com.example.register.data.remote.service.FieldFetchService
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
    fun provideFieldFetchService(retrofit: Retrofit): FieldFetchService =
        retrofit.create(FieldFetchService::class.java)

}
