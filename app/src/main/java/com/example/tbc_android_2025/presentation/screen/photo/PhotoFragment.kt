package com.example.tbc_android_2025.presentation.screen.photo

import androidx.fragment.app.viewModels
import com.example.tbc_android_2025.databinding.LayoutBottomSheetBinding
import com.example.tbc_android_2025.presentation.common.Colors
import com.example.tbc_android_2025.presentation.common.Strings
import com.example.tbc_android_2025.databinding.FragmentPhotoBinding as Binding
import com.example.tbc_android_2025.presentation.common.fragment.BaseMviFragment
import com.example.tbc_android_2025.presentation.extension.gone
import com.example.tbc_android_2025.presentation.extension.popMessage
import com.example.tbc_android_2025.presentation.extension.show
import com.example.tbc_android_2025.presentation.screen.photo.PhotoContract.*
import com.example.tbc_android_2025.presentation.util.PhotoPicker
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoFragment :
    BaseMviFragment<Binding, State, SideEffect, PhotoViewModel>(inflater = Binding::inflate) {

    override val viewModel: PhotoViewModel by viewModels()

    private lateinit var photoPicker: PhotoPicker


    override fun bind() {
        photoPicker = PhotoPicker(
            fragment = this,
            onPhotoPicked = { viewModel.onEvent(Event.OnPhotoSelected(uri = it)) },
            onPermissionDenied = {
                binding.addPhotoButton.popMessage(
                    resId = Strings.camera_permission_is_required,
                    color = Colors.amaranth
                )
            }
        )
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
        SideEffect.ShowUploadSuccess -> binding.uploadButton.popMessage(
            text = "Photo Uploaded Successfully!",
            color = Colors.viridian
        )
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
            photoPicker.requestCameraPermission()
            dialog.dismiss()
        }

        sheetBinding.galleryButton.setOnClickListener {
            photoPicker.openGallery()
            dialog.dismiss()
        }

        dialog.setContentView(sheetBinding.root)
        dialog.show()
    }

    /** ========================================================================================= */


    override fun onDestroyView() {
        super.onDestroyView()
        photoPicker.clear()
    }
}
