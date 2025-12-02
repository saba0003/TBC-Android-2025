package com.example.tbc_android_2025.di

import com.example.tbc_android_2025.domain.commons.ResourceProvider
import com.example.tbc_android_2025.utils.StringProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Binds
    @Singleton
    fun bindResourceProvider(stringProvider: StringProvider): ResourceProvider

}
