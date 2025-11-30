package com.example.tbc_android_2025.di

import com.example.tbc_android_2025.data.repositories.LogInRepositoryImpl
import com.example.tbc_android_2025.data.repositories.RegisterRepositoryImpl
import com.example.tbc_android_2025.data.repositories.UserRepositoryImpl
import com.example.tbc_android_2025.domain.repositories.LogInRepository
import com.example.tbc_android_2025.domain.repositories.UserRepository
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
    fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    fun bindRegisterRepository(registerRepositoryImpl: RegisterRepositoryImpl): LogInRepository

    @Binds
    @Singleton
    fun bindLogInRepository(logInRepositoryImpl: LogInRepositoryImpl): LogInRepository

}
