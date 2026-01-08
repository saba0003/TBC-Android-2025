package com.example.tbc_android_2025.presentation.extension

import android.content.Context
import androidx.core.content.ContextCompat.getString
import com.example.tbc_android_2025.presentation.common.Strings
import com.example.tbc_android_2025.domain.error.AppError

fun AppError.asString(context: Context) = when (this) {
    AppError.Network -> getString(context, Strings.error_network)
    AppError.ServiceUnavailable -> getString(context, Strings.error_server)
    AppError.NotFound -> getString(context, Strings.error_not_found)
    AppError.Forbidden -> getString(context, Strings.error_forbidden)
    AppError.Unauthorized -> getString(context, Strings.error_login_required)
    is AppError.ApiError -> message ?: getString(context, Strings.error_api)
    is AppError.Technical -> message ?: getString(context, Strings.error_technical)
    AppError.Unknown -> getString(context, Strings.error_unknown)
}
