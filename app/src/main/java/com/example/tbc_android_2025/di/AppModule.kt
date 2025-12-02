package com.example.tbc_android_2025.di

import android.content.Context
import com.example.tbc_android_2025.utils.StringProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideStringProvider(@ApplicationContext context: Context) =
        StringProvider(context = context)

}
