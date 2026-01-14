package com.example.tbc_android_2025.domain.use_case.local

import com.example.tbc_android_2025.domain.model.ProcessedPhoto

interface ProcessPhotoUseCase {
    suspend operator fun invoke(uri: String): Result<ProcessedPhoto>
}
