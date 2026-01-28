package com.example.tbc_android_2025.data.local.data_store

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.tbc_android_2025.domain.data_store.DataStoreManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreManagerImpl @Inject constructor(private val dataStore: DataStore<Preferences>) :
    DataStoreManager {

    override fun <T> getPreference(key: Preferences.Key<T>, defaultValue: T): Flow<T> =
        dataStore.data.map { it[key] ?: defaultValue }

    override suspend fun <T> setPreference(key: Preferences.Key<T>, value: T) {
        dataStore.edit { it[key] = value }
    }

    override suspend fun removePreferences(keys: List<Preferences.Key<*>>) {
        dataStore.edit { preferences -> keys.forEach { key -> preferences.remove(key = key) } }
    }
}
