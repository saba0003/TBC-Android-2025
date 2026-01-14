package com.example.tbc_android_2025.presentation.screen.photo

import androidx.lifecycle.viewModelScope
import com.example.tbc_android_2025.domain.error.AppError
import com.example.tbc_android_2025.presentation.common.view_model.BaseViewModel
import com.example.tbc_android_2025.presentation.screen.photo.PhotoContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.tbc_android_2025.domain.use_case.local.ProcessPhotoUseCase
import com.example.tbc_android_2025.domain.use_case.remote.PhotoUploadUseCase

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val processPhotoUseCase: ProcessPhotoUseCase,
    private val photoUploadUseCase: PhotoUploadUseCase
) : BaseViewModel<State, Event, SideEffect>(initialState = State.loading()) {

    init {
        updateState { copy(isLoading = isLoading.not()) }
    }

    override fun onEvent(event: Event) = when (event) {
        Event.OnAddPhotoClicked -> emitSideEffect(sideEffect = SideEffect.ShowImagePickerOptions)
        Event.OnUploadClicked -> uploadImage()
        is Event.OnPhotoSelected -> processImage(uri = event.uri)
    }

    private fun processImage(uri: String) {
        viewModelScope.launch {
            updateState { copy(isLoading = isLoading.not()) }

            processPhotoUseCase(uri = uri)
                .onSuccess { updateState { copy(processedPhotoUri = it.uri) } }
                .onFailure {
                    emitSideEffect(SideEffect.ShowError(error = AppError.PhotoProcess(message = it.message)))
                }

            updateState { copy(photoUri = uri, isLoading = isLoading.not()) }
        }
    }

    private fun uploadImage() {
//        viewModelScope.launch(context = Dispatchers.IO) {
//            val photoUri = state.value.processedPhotoUri ?: return@launch
//
//            updateState { copy(isLoading = true) }
//
//            photoUploadUseCase(bitmap = photoUri)
//                .onSuccess { url ->
//                    updateState { copy(isLoading = false) }
//                    emitSideEffect(sideEffect = SideEffect.ShowUploadSuccess)
//                }
//                .onFailure {
//                    updateState { copy(isLoading = false) }
//                    emitSideEffect(
//                        sideEffect = SideEffect.ShowError(error = AppError.PhotoUpload(message = it.message))
//                    )
//                }
//        }
    }
}
