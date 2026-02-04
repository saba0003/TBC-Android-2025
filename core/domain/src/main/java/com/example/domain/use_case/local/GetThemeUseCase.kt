package com.example.domain.use_case.local

import com.example.domain.repository.SettingsRepository
import javax.inject.Inject

class GetThemeUseCase @Inject constructor(private val repository: SettingsRepository) {

    operator fun invoke() = repository.isDarkMode()

}
