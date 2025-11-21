package com.example.tbc_android_2025.data.auth

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.example.tbc_android_2025.data.HttpStrings.PREF_NAME

val Context.sessionDataStore by preferencesDataStore(name = PREF_NAME)
