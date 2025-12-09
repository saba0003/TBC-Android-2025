package com.example.tbc_android_2025.di.modules

import com.example.tbc_android_2025.data.repositories.PostRemoteRepositoryImpl
import com.example.tbc_android_2025.di.qualifiers.RemoteRepository
import com.example.tbc_android_2025.domain.repositories.PostRepository
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
    @RemoteRepository
    fun bindPostRemoteRepository(impl: PostRemoteRepositoryImpl): PostRepository

}
