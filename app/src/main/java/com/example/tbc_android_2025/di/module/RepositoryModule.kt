package com.example.tbc_android_2025.di.module

import com.example.tbc_android_2025.data.repository.LoginRepositoryImpl
import com.example.tbc_android_2025.data.repository.RegisterRepositoryImpl
import com.example.tbc_android_2025.data.repository.UserRepositoryImpl
import com.example.tbc_android_2025.domain.repository.LoginRepository
import com.example.tbc_android_2025.domain.repository.RegisterRepository
import com.example.tbc_android_2025.domain.repository.UserRepository
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
    fun bindRegisterRepository(impl: RegisterRepositoryImpl): RegisterRepository

    @Binds
    @Singleton
    fun bindLoginRepository(impl: LoginRepositoryImpl): LoginRepository

    @Binds
    @Singleton
    fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

}
