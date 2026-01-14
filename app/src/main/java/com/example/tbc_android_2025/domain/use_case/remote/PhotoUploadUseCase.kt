package com.example.tbc_android_2025.domain.use_case.remote

import android.graphics.Bitmap
import com.example.tbc_android_2025.domain.repository.PhotoRepository
import javax.inject.Inject

class PhotoUploadUseCase @Inject constructor(
    private val repository: PhotoRepository
) {
    suspend operator fun invoke(bitmap: Bitmap?): Result<String> =
        bitmap?.let { repository.uploadPhoto(bitmap = it) }
            ?: Result.failure(exception = Exception("No photo selected"))
}
