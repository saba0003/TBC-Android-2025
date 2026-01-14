package com.example.tbc_android_2025.domain.use_case.remote

import android.graphics.Bitmap
import com.example.tbc_android_2025.domain.repository.PhotoRepository
import javax.inject.Inject

class PhotoUploadUseCase @Inject constructor(
    private val repository: PhotoRepository
) {
    suspend operator fun invoke(bitmap: Bitmap?): Result<String> {
        if (bitmap == null)
            return Result.failure(Exception("No photo selected"))
        return repository.uploadPhoto(bitmap = bitmap)
    }
}
