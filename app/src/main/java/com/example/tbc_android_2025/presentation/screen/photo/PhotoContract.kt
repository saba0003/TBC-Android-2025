package com.example.tbc_android_2025.presentation.screen.photo

import android.graphics.Bitmap
import android.net.Uri
import androidx.core.net.UriCompat
import com.example.tbc_android_2025.domain.error.AppError

interface PhotoContract {
    data class State(
        val photoUri: Uri? = null,
        val processedBitmap: Bitmap? = null,
        val isLoading: Boolean = false
    ) {
        companion object {
            fun loading() = State(isLoading = true)
        }
    }

    sealed interface Event {
        data object OnAddPhotoClicked : Event
        data object OnUploadClicked : Event
        data class OnPhotoSelected(val uri: Uri) : Event
    }

    sealed interface SideEffect {
        data object ShowImagePickerOptions : SideEffect
        data class ShowError(val message: String) : SideEffect
    }
}
