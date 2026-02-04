package com.example.data.repository

import com.example.domain.data_store.DataStoreKeys
import com.example.domain.data_store.DataStoreManager
import com.example.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(private val dataStoreManager: DataStoreManager) :
    SettingsRepository {

    override fun isDarkMode(): Flow<Boolean> =
        dataStoreManager
            .getPreference(key = DataStoreKeys.DARK_MODE_KEY, defaultValue = DARK_MODE_AS_DEFAULT)

    override suspend fun setThemeMode(isDarkMode: Boolean) =
        dataStoreManager.setPreference(key = DataStoreKeys.DARK_MODE_KEY, value = isDarkMode)

    private companion object {
        const val DARK_MODE_AS_DEFAULT = true
    }
}
