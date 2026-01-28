package com.example.tbc_android_2025.data.repository

import com.example.tbc_android_2025.domain.data_store.DataStoreKeys
import com.example.tbc_android_2025.domain.data_store.DataStoreManager
import com.example.tbc_android_2025.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(private val dataStoreManager: DataStoreManager) :
    SettingsRepository {

    override fun isDarkMode(): Flow<Boolean> =
        dataStoreManager.getPreference(
            key = DataStoreKeys.DARK_MODE_KEY, defaultValue = DARK_MODE_AS_DEFAULT
        )

    override suspend fun setThemeMode(isDark: Boolean) =
        dataStoreManager.setPreference(key = DataStoreKeys.DARK_MODE_KEY, value = isDark)

    private companion object {
        const val DARK_MODE_AS_DEFAULT = true
    }
}
