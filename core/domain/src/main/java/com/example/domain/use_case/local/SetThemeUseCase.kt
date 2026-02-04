package com.example.domain.use_case.local

import com.example.domain.repository.SettingsRepository
import javax.inject.Inject

class SetThemeUseCase @Inject constructor(private val repository: SettingsRepository) {

    suspend operator fun invoke(isDarkMode: Boolean) =
        repository.setThemeMode(isDarkMode = isDarkMode)

}
