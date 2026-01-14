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

    suspend fun process(uri: String): Result<ProcessedPhoto> =
        withContext(context = Dispatchers.IO) {
            runCatching {
                val originalBitmap = decodeUriToBitmap(uri = uri)
                val scaledBitmap = scaleBitmap(bitmap = originalBitmap)
                val destinationFile = createTempFile()
                saveBitmapToFile(bitmap = scaledBitmap, file = destinationFile)
                ProcessedPhoto(uri = destinationFile.toUri().toString())
            }
        }


    /** ========================================== AUX ========================================== */
    private fun decodeUriToBitmap(uri: String): Bitmap =
        context.contentResolver.openInputStream(uri.toUri())?.use {
            BitmapFactory.decodeStream(it)
        } ?: throw IllegalStateException("Failed to open or decode stream")

    private fun scaleBitmap(bitmap: Bitmap): Bitmap = with(receiver = bitmap) {
        val newWidth = (width * RESOLUTION_SCALE).toInt()
        val newHeight = (height * RESOLUTION_SCALE).toInt()
        return scale(width = newWidth, height = newHeight)
    }

    @OptIn(ExperimentalUuidApi::class)
    private fun createTempFile(): File {
        val directory = File(context.cacheDir, TEMP_DIR_NAME).also {
            if (!it.exists() && !it.mkdirs())
                throw IllegalStateException("Failed to create cache directory")
        }
        val fileName = PHOTO_PREFIX + Uuid.random() + PHOTO_EXTENSION
        return File(directory, fileName)
    }

    private fun saveBitmapToFile(bitmap: Bitmap, file: File) {
        val format = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
            Bitmap.CompressFormat.WEBP_LOSSY
        else
            Bitmap.CompressFormat.WEBP

        FileOutputStream(file).use { bitmap.compress(format, COMPRESSION_QUALITY, it) }
    }
    /** ========================================================================================= */


    private companion object {
        const val RESOLUTION_SCALE = 0.8
        const val COMPRESSION_QUALITY = 80
        const val TEMP_DIR_NAME = "processed_images"
        const val PHOTO_PREFIX = "processed_photo_"
        const val PHOTO_EXTENSION = ".webp"
    }
}
