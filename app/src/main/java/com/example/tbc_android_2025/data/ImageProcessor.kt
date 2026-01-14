package com.example.tbc_android_2025.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.graphics.scale
import androidx.core.net.toUri
import com.example.tbc_android_2025.domain.model.ProcessedPhoto
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class ImageProcessor @Inject constructor(@param:ApplicationContext private val context: Context) {

    @OptIn(ExperimentalUuidApi::class)
    suspend fun process(uri: String): Result<ProcessedPhoto> =
        withContext(context = Dispatchers.Default) {
            try {
                val file = File(
                    context.cacheDir,
                    PHOTO_PREFIX.plus(other = Uuid.random()).plus(other = PHOTO_EXTENSION)
                )

                val inputStream = context.contentResolver.openInputStream(uri.toUri())
                val bitmap = BitmapFactory.decodeStream(inputStream)
                inputStream?.close()

                val scaled = bitmap.scale(
                    width = (bitmap.width * RESOLUTION_SCALE).toInt(),
                    height = (bitmap.height * RESOLUTION_SCALE).toInt()
                )

                val format = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
                    Bitmap.CompressFormat.WEBP_LOSSY
                else
                    Bitmap.CompressFormat.WEBP

                FileOutputStream(file).use {
                    scaled.compress(format, COMPRESSION_QUALITY, it)
                }

                Result.success(value = ProcessedPhoto(uri = file.toUri().toString()))
            } catch (e: Exception) {
                Result.failure(exception = e)
            }
        }

    private companion object {
        const val RESOLUTION_SCALE = 0.8
        const val COMPRESSION_QUALITY = 80
        const val PHOTO_PREFIX = "processed_photo_"
        const val PHOTO_EXTENSION = ".webp"
    }
}
