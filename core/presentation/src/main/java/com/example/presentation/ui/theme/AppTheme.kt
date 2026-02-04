package com.example.presentation.ui.theme

import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import com.example.presentation.ui.color.AppColors

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
