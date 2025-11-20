package com.example.tbc_android_2025.data.auth

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.tbc_android_2025.data.HttpStrings.PREF_NAME

val Context.sessionDataStore: DataStore<Preferences> by preferencesDataStore(name = PREF_NAME)
