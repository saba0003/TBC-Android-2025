package com.example.tbc_android_2025.domain.repository

import android.graphics.Bitmap

interface PhotoRepository {
    suspend fun uploadPhoto(bitmap: Bitmap): Result<String>
}
