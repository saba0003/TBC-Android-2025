package com.example.tbc_android_2025.presentation.screen.photo

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.example.tbc_android_2025.presentation.common.view_model.BaseViewModel
import com.example.tbc_android_2025.presentation.screen.photo.PhotoContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import javax.inject.Inject
import androidx.core.graphics.scale
import com.example.tbc_android_2025.domain.use_case.remote.PhotoUploadUseCase
import com.example.tbc_android_2025.presentation.common.Strings

@HiltViewModel
class PhotoViewModel @Inject constructor(
    @param:ApplicationContext private val context: Context,
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

    private fun processImage(uri: Uri) {
        viewModelScope.launch(context = Dispatchers.Default) {
            updateState { copy(isLoading = isLoading.not()) }
            try {
                // 1. Get original bitmap
                val inputStream = context.contentResolver.openInputStream(uri)
                val originalBitmap = BitmapFactory.decodeStream(inputStream)
                inputStream?.close()

                // 2. Reduce Resolution by 20% (Scale to 80% of original)
                val newWidth = (originalBitmap.width * 0.8).toInt()
                val newHeight = (originalBitmap.height * 0.8).toInt()
                val scaledBitmap = originalBitmap.scale(width = newWidth, height = newHeight)

                // 3. Reduce Quality by 20% (Compress to 80% quality)
                val outputStream = ByteArrayOutputStream()
                scaledBitmap.compress(Bitmap.CompressFormat.WEBP, 80, outputStream)
                val compressedByteArray = outputStream.toByteArray()

                // Convert back to bitmap to display in ImageView
                val finalBitmap =
                    BitmapFactory.decodeByteArray(compressedByteArray, 0, compressedByteArray.size)

                updateState { copy(processedBitmap = finalBitmap) }
            } catch (e: Exception) {
                emitSideEffect(
                    sideEffect = SideEffect.ShowError(
                        message = e.message ?: context.getString(
                            Strings.error_unknown
                        )
                    )
                )
            } finally {
                updateState { copy(isLoading = isLoading.not(), photoUri = uri) }
            }
        }
    }

    private fun uploadImage() {
        viewModelScope.launch(Dispatchers.IO) {
            // Only upload if we have a bitmap
            val bitmap = state.value.processedBitmap ?: return@launch

            updateState { copy(isLoading = true) }

            photoUploadUseCase(bitmap)
                .onSuccess { url ->
                    updateState { copy(isLoading = false) }
                    emitSideEffect(SideEffect.ShowUploadSuccess)
                }
                .onFailure { error ->
                    updateState { copy(isLoading = false) }
                    emitSideEffect(
                        SideEffect.ShowError(
                            message = error.message ?: "Upload failed"
                        )
                    )
                }
        }
    }
}
