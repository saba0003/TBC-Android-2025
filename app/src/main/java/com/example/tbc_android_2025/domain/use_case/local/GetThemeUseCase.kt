package com.example.tbc_android_2025.domain.use_case.local

import com.example.tbc_android_2025.domain.repository.SettingsRepository
import javax.inject.Inject

class GetThemeUseCase @Inject constructor(private val repository: SettingsRepository) {

    operator fun invoke() = repository.isDarkMode()

}
