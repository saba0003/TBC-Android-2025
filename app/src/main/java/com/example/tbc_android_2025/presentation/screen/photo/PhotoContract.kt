package com.example.tbc_android_2025.presentation.screen.photo

import com.example.tbc_android_2025.domain.error.AppError

interface PhotoContract {
    data class State(
        val photoUri: String? = null,
        val processedPhotoUri: String? = null,
        val isLoading: Boolean = false
    ) {
        companion object {
            fun loading() = State(isLoading = true)
        }
    }

    sealed interface Event {
        data object OnAddPhotoClicked : Event
        data object OnUploadClicked : Event
        data class OnPhotoSelected(val uri: String) : Event
    }

    sealed interface SideEffect {
        data object ShowImagePickerOptions : SideEffect
        data object ShowUploadSuccess : SideEffect
        data class ShowError(val error: AppError) : SideEffect
    }
}
