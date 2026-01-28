package com.example.tbc_android_2025.domain.use_case.data_store

import androidx.datastore.preferences.core.Preferences
import com.example.tbc_android_2025.domain.data_store.DataStoreManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPreferenceUseCase @Inject constructor(private val preferencesRepository: DataStoreManager) {

    operator fun <T> invoke(key: Preferences.Key<T>, defaultValue: T): Flow<T> =
        preferencesRepository.getPreference(key = key, defaultValue = defaultValue)

}
