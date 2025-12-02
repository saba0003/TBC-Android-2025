package com.example.tbc_android_2025.di

import com.example.tbc_android_2025.data.repositories.TemplateRepositoryImpl
import com.example.tbc_android_2025.domain.repositories.TemplateRepository
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
    fun bindTemplateRepository(templateRepositoryImpl: TemplateRepositoryImpl): TemplateRepository

}
