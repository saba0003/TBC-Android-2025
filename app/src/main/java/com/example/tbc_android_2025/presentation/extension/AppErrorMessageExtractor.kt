package com.example.tbc_android_2025.presentation.extension

import android.content.Context
import androidx.core.content.ContextCompat.getString
import com.example.tbc_android_2025.presentation.common.Strings
import com.example.tbc_android_2025.domain.error.AppError

fun AppError.asString(context: Context) = when (this) {
    AppError.Unknown -> getString(context, Strings.error_unknown)
}
