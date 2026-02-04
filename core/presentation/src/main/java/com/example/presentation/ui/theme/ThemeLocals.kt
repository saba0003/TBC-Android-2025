package com.example.presentation.ui.theme

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.presentation.ui.color.AppColors
import com.example.presentation.ui.typography.AppTypography

object ThemeLocals {
    private const val ERROR_COLOR_SCHEME_NOT_PROVIDED = "No ColorScheme provided."
    private const val ERROR_SNACKBAR_HOST_STATE_NOT_PROVIDED = "No SnackbarHostState provided."


    internal val LocalColorScheme = staticCompositionLocalOf<AppColors.Scheme> {
        error(message = ERROR_COLOR_SCHEME_NOT_PROVIDED)
    }

    internal val LocalAppTypography = staticCompositionLocalOf { AppTypography.Typography }

    internal val LocalSnackbarHostState = compositionLocalOf<SnackbarHostState> {
        error(message = ERROR_SNACKBAR_HOST_STATE_NOT_PROVIDED)
    }
}
