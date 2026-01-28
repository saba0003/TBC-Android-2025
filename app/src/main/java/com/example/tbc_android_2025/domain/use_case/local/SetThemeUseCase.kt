package com.example.tbc_android_2025.domain.use_case.local

import com.example.tbc_android_2025.domain.repository.SettingsRepository
import javax.inject.Inject

class SetThemeUseCase @Inject constructor(private val repository: SettingsRepository) {

    suspend operator fun invoke(isDark: Boolean) = repository.setThemeMode(isDark = isDark)

}
