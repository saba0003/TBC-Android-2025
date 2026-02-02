package com.example.tbc_android_2025.presentation.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.tbc_android_2025.design.global.AppColors
import com.example.tbc_android_2025.design.global.AppTypography

private const val DOES_NOT_SUPPORT_DYNAMIC_COLOR_BY_DEFAULT = false

@Composable
fun TBCAndroid2025Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = DOES_NOT_SUPPORT_DYNAMIC_COLOR_BY_DEFAULT,
    content: @Composable () -> Unit
) {
    val customColorScheme = if (darkTheme) AppColors.DarkThemeScheme else AppColors.LightThemeScheme

    val supportsDynamicColor =
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

    val colorScheme = when {
        supportsDynamicColor -> {
            val context = LocalContext.current
            if (darkTheme)
                dynamicDarkColorScheme(context = context)
            else
                dynamicLightColorScheme(context = context)
        }

        darkTheme -> darkColorScheme(
            primary = customColorScheme.primary,
            onPrimary = customColorScheme.onPrimary,
            background = customColorScheme.background,
            onBackground = customColorScheme.onBackground,
            surface = customColorScheme.surface,
            onSurface = customColorScheme.onSurface
        )

        else -> lightColorScheme(
            primary = customColorScheme.primary,
            onPrimary = customColorScheme.onPrimary,
            background = customColorScheme.background,
            onBackground = customColorScheme.onBackground,
            surface = customColorScheme.surface,
            onSurface = customColorScheme.onSurface
        )
    }

    val snackbarHostState = remember { SnackbarHostState() }

    CompositionLocalProvider(
        ThemeLocals.LocalColorScheme provides customColorScheme,
        ThemeLocals.LocalAppTypography provides AppTypography.Typography,
        ThemeLocals.LocalSnackbarHostState provides snackbarHostState
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = AppTypography.Typography,
            content = content
        )
    }
}
