package com.example.tbc_android_2025.presentation.extension

import android.content.Context
import androidx.core.content.ContextCompat.getString
import com.example.tbc_android_2025.presentation.common.Strings
import com.example.tbc_android_2025.domain.error.AppError

fun AppError.asString(context: Context) = when (this) {
    AppError.Network -> getString(context, Strings.error_network)
    is AppError.PhotoProcess -> message ?: getString(context, Strings.error_photo_process)
    is AppError.PhotoUpload -> message ?: getString(context, Strings.error_photo_upload)
    AppError.Unknown -> getString(context, Strings.error_unknown)
}
