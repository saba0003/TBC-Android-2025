package com.example.tbc_android_2025.domain.repository

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun isDarkMode(): Flow<Boolean>
    suspend fun setThemeMode(isDark: Boolean)
}
