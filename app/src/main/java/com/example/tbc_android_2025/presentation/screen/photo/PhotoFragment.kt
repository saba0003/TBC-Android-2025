package com.example.tbc_android_2025.presentation.screen.photo

import android.net.Uri
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.viewModels
import com.example.tbc_android_2025.BuildConfig.FILE_PROVIDER_SUFFIX
import com.example.tbc_android_2025.databinding.LayoutBottomSheetBinding
import com.example.tbc_android_2025.presentation.common.Colors
import com.example.tbc_android_2025.databinding.FragmentPhotoBinding as Binding
import com.example.tbc_android_2025.presentation.common.fragment.BaseMviFragment
import com.example.tbc_android_2025.presentation.extension.gone
import com.example.tbc_android_2025.presentation.extension.popMessage
import com.example.tbc_android_2025.presentation.extension.show
import com.example.tbc_android_2025.presentation.screen.photo.PhotoContract.*
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class PhotoFragment :
    BaseMviFragment<Binding, State, SideEffect, PhotoViewModel>(inflater = Binding::inflate) {

    override val viewModel: PhotoViewModel by viewModels()

    private val galleryLauncher =
        registerForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
            uri?.let { viewModel.onEvent(event = Event.OnPhotoSelected(uri = it)) }
        }
    private var tempImageUri: Uri? = null
    private val cameraLauncher =
        registerForActivityResult(contract = ActivityResultContracts.TakePicture()) { success ->
            if (success)
                tempImageUri?.let { viewModel.onEvent(event = Event.OnPhotoSelected(uri = it)) }
        }


    override fun bindViewActionListeners() {
        setListenerOnAddPhotoButton()
        setListenerOnUploadButton()
    }


    /** ======================================= LISTENERS ======================================= */
    private fun setListenerOnAddPhotoButton() = binding.addPhotoButton.setOnClickListener {
        viewModel.onEvent(event = Event.OnAddPhotoClicked)
    }

    private fun setListenerOnUploadButton() = binding.uploadButton.setOnClickListener {
        viewModel.onEvent(event = Event.OnUploadClicked)
    }
    /** ========================================================================================= */


    /** ======================================= HANDLERS ======================================== */
    override fun handleStates(state: State) {
        if (state.isLoading)
            binding.loaderInclude.loaderContainer.show()
        else
            binding.loaderInclude.loaderContainer.gone()
        state.processedBitmap?.let {
            binding.selectedPhotoImageView.setImageBitmap(it)
        }
    }

    override fun handleSideEffects(sideEffect: SideEffect) = when (sideEffect) {
        SideEffect.ShowImagePickerOptions -> showBottomSheet()
        is SideEffect.ShowError -> binding.addPhotoButton.popMessage(
            text = sideEffect.message, color = Colors.amaranth
        )
    }
    /** ========================================================================================= */


    /** ========================================== AUX ========================================== */
    private fun showBottomSheet() {
        val dialog = BottomSheetDialog(requireContext())
        val sheetBinding = LayoutBottomSheetBinding.inflate(layoutInflater)

        sheetBinding.cameraButton.setOnClickListener {
            openCamera()
            dialog.dismiss()
        }

        sheetBinding.galleryButton.setOnClickListener {
            galleryLauncher.launch(input = IMAGE_MIME_TYPE)
            dialog.dismiss()
        }

        dialog.setContentView(sheetBinding.root)
        dialog.show()
    }

    private fun openCamera() {
        val file = File(
            requireContext().cacheDir,
            PHOTO_PREFIX.plus(other = System.currentTimeMillis()).plus(other = PHOTO_EXTENSION)
        )
        tempImageUri = FileProvider.getUriForFile(
            requireContext(),
            requireContext().packageName.plus(other = FILE_PROVIDER_SUFFIX),
            file
        )
        cameraLauncher.launch(input = tempImageUri)
    }

    /** ========================================================================================= */

    private companion object {
        const val IMAGE_MIME_TYPE = "image/*"
        const val PHOTO_PREFIX = "temp_photo_"
        const val PHOTO_EXTENSION = ".jpg"
    }
}
