package com.example.tbc_android_2025.presentation.ui.theme

import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import com.example.tbc_android_2025.design.global.AppColors

object AppTheme {
    val Colors: AppColors.Scheme
        @Composable
        get() = ThemeLocals.LocalColorScheme.current

    val Typography: Typography
        @Composable
        get() = ThemeLocals.LocalAppTypography.current

    val SnackbarHostState: SnackbarHostState
        @Composable
        get() = ThemeLocals.LocalSnackbarHostState.current
}
