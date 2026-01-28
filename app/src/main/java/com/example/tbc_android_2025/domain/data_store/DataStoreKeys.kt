package com.example.tbc_android_2025.domain.data_store

import androidx.datastore.preferences.core.booleanPreferencesKey

object DataStoreKeys {
    private const val IS_DARK_MODE = "is_dark_mode"

    val DARK_MODE_KEY = booleanPreferencesKey(name = IS_DARK_MODE)
}
