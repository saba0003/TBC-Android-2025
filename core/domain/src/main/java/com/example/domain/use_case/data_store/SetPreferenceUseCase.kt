package com.example.domain.use_case.data_store

import androidx.datastore.preferences.core.Preferences
import com.example.domain.data_store.DataStoreManager
import javax.inject.Inject

class SetPreferenceUseCase @Inject constructor(private val preferencesRepository: DataStoreManager) {

    suspend operator fun <T> invoke(key: Preferences.Key<T>, value: T) =
        preferencesRepository.setPreference(key = key, value = value)

}
