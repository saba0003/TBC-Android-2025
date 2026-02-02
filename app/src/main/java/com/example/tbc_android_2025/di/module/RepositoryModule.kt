package com.example.tbc_android_2025.di.module

import com.example.tbc_android_2025.data.repository.*
import com.example.tbc_android_2025.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindFieldRepository(impl: FieldRepositoryImpl): FieldRepository

    @Binds
    @Singleton
    fun bindSettingsRepository(impl: SettingsRepositoryImpl): SettingsRepository

}
