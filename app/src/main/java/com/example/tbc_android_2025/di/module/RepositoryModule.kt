package com.example.tbc_android_2025.di.module

import com.example.tbc_android_2025.data.repository.MovieRemoteRepositoryImpl
import com.example.tbc_android_2025.di.qualifier.RemoteRepository
import com.example.tbc_android_2025.domain.repository.MovieRepository
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
    fun bindMovieRemoteRepository(impl: MovieRemoteRepositoryImpl): MovieRepository

}
