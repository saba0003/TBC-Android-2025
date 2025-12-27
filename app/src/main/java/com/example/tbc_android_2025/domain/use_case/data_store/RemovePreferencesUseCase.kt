package com.example.tbc_android_2025.domain.use_case.data_store

import androidx.datastore.preferences.core.Preferences
import com.example.tbc_android_2025.domain.data_store.DataStoreManager
import javax.inject.Inject

class RemovePreferencesUseCase @Inject constructor(private val preferencesRepository: DataStoreManager) {

    suspend operator fun invoke(keys: List<Preferences.Key<*>>) =
        preferencesRepository.removePreferences(keys = keys)

}
