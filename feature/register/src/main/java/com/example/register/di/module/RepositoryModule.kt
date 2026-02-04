package com.example.register.di.module

import com.example.register.data.repository.FieldRepositoryImpl
import com.example.register.domain.repository.FieldRepository
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

}
