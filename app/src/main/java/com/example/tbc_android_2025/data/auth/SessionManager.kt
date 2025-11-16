package com.example.tbc_android_2025.data.auth

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.tbc_android_2025.data.HttpStrings.AUTH_TOKEN
import com.example.tbc_android_2025.data.sessionDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object SessionManager {

    private val KEY_AUTH_TOKEN = stringPreferencesKey(name = AUTH_TOKEN)

    fun tokenFlow(context: Context): Flow<String?> =
        context.sessionDataStore.data.map { it[KEY_AUTH_TOKEN] }

    suspend fun saveToken(context: Context, token: String): Preferences =
        context.sessionDataStore.edit { it[KEY_AUTH_TOKEN] = token }

    suspend fun clear(context: Context) = context.sessionDataStore.edit { it.clear() }
}
