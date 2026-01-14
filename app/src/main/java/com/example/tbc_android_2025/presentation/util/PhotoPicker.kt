package com.example.tbc_android_2025.presentation.util

import android.Manifest.permission.CAMERA
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.activity.result.contract.ActivityResultContracts.TakePicture
import androidx.activity.result.contract.ActivityResultContracts.GetContent
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.example.tbc_android_2025.BuildConfig.FILE_PROVIDER_SUFFIX
import java.io.File
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class PhotoPicker(
    fragment: Fragment,
    private val onPhotoPicked: (Uri) -> Unit,
    private val onPermissionDenied: () -> Unit
) {

    private var tempImageUri: Uri? = null

    private val permissionLauncher = fragment.registerForActivityResult(RequestPermission()) {
        if (it)
            openCamera(fragment = fragment)
        else
            onPermissionDenied()
    }

    private val galleryLauncher =
        fragment.registerForActivityResult(GetContent()) { it?.let(block = onPhotoPicked) }

    private val cameraLauncher = fragment.registerForActivityResult(TakePicture()) { granted ->
        tempImageUri?.takeIf { granted }?.let(block = onPhotoPicked)
    }


    fun openGallery() = galleryLauncher.launch(input = IMAGE_MIME_TYPE)

    fun requestCameraPermission() = permissionLauncher.launch(input = CAMERA)

    fun clear() {
        tempImageUri = null
    }


    /** AUX */
    @OptIn(ExperimentalUuidApi::class)
    private fun openCamera(fragment: Fragment) {
        val file = File(
            fragment.requireContext().cacheDir,
            PHOTO_PREFIX.plus(other = Uuid.random()).plus(other = PHOTO_EXTENSION)
        )

        tempImageUri = FileProvider.getUriForFile(
            fragment.requireContext(),
            fragment.requireContext().packageName.plus(other = FILE_PROVIDER_SUFFIX),
            file
        )

        cameraLauncher.launch(input = tempImageUri)
    }

    private companion object {
        const val IMAGE_MIME_TYPE = "image/*"
        const val PHOTO_PREFIX = "temp_photo_"
        const val PHOTO_EXTENSION = ".jpg"
    }
}
