package com.example.tbc_android_2025.di.modules

import com.example.tbc_android_2025.data.remote.network.ConnectivityObserverImpl
import com.example.tbc_android_2025.domain.network.ConnectivityObserver
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ConnectivityModule {

    @Binds
    @Singleton
    fun bindConnectivityObserver(impl: ConnectivityObserverImpl): ConnectivityObserver

}
