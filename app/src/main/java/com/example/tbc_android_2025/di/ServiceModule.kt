package com.example.tbc_android_2025.di

import com.example.tbc_android_2025.data.remote.services.TemplateService
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
    fun provideTemplateService(retrofit: Retrofit): TemplateService =
        retrofit.create(TemplateService::class.java)

}
