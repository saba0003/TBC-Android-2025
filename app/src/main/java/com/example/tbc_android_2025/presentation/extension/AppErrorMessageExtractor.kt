package com.example.tbc_android_2025.presentation.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.tbc_android_2025.presentation.common.Strings
import com.example.tbc_android_2025.domain.error.AppError

@Composable
fun AppError.asString(): String = when (this) {
    AppError.Network -> stringResource(id = Strings.error_network)
    AppError.ServiceUnavailable -> stringResource(id = Strings.error_server)
    AppError.NotFound -> stringResource(id = Strings.error_not_found)
    AppError.Forbidden -> stringResource(id = Strings.error_forbidden)
    AppError.Unauthorized -> stringResource(id = Strings.error_login_required)
    is AppError.ApiError -> message ?: stringResource(id = Strings.error_api)
    is AppError.Technical -> message ?: stringResource(id = Strings.error_technical)
    AppError.Unknown -> stringResource(id = Strings.error_unknown)
}
