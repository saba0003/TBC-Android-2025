package com.example.tbc_android_2025.di

import android.content.Context
import androidx.room.Room
import com.example.tbc_android_2025.data.local.db.AppDatabase
import com.example.tbc_android_2025.data.local.db.DatabaseConstants.USER_DATABASE
import com.example.tbc_android_2025.data.local.users.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            name = USER_DATABASE
        ).build()

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase): UserDao = appDatabase.userDao()

}
