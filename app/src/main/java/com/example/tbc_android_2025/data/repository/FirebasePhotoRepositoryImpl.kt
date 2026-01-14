package com.example.tbc_android_2025.data.repository

import android.graphics.Bitmap
import com.example.tbc_android_2025.domain.repository.PhotoRepository
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import com.google.firebase.storage.storageMetadata
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class FirebasePhotoRepositoryImpl @Inject constructor() : PhotoRepository {

    private val storage = Firebase.storage.reference

    @OptIn(ExperimentalUuidApi::class)
    override suspend fun uploadPhoto(bitmap: Bitmap): Result<String> = try {
        val fileName = "uploads/${Uuid.random()}.webp"
        val ref = storage.child(fileName)

        // Convert bitmap to byte array
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.WEBP, 80, baos)
        val data = baos.toByteArray()

        // 1. Create Metadata
        val metadata = storageMetadata {
            contentType = "image/webp"
        }

        // 2. Upload with metadata and await completion
        ref.putBytes(data, metadata).await()

        // 3. Get URL
        val downloadUrl = ref.downloadUrl.await().toString()

        Result.success(downloadUrl)
    } catch (e: Exception) {
        Result.failure(e)
    }
}
