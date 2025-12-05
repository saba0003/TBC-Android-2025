package com.example.tbc_android_2025.di

import com.example.tbc_android_2025.data.repositories.CardRepositoryImpl
import com.example.tbc_android_2025.domain.repositories.CardRepository
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
    fun bindCardRepository(cardRepositoryImpl: CardRepositoryImpl): CardRepository

}
