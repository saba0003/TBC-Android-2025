package com.example.tbc_android_2025.di.modules

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.tbc_android_2025.data.local.data_store.DataStoreManagerImpl
import com.example.tbc_android_2025.domain.data_store.DataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    private const val APP_SESSION = "app_session"


    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(produceFile = { context.preferencesDataStoreFile(name = APP_SESSION) })

    @Provides
    @Singleton
    fun bindDataStoreManager(impl: DataStoreManagerImpl): DataStoreManager = impl

}
