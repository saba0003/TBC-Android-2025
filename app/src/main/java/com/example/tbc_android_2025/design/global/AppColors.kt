package com.example.tbc_android_2025.design.global

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

object AppColors {
    private const val LIGHT_GREEN = 0xFF70ED63
    private const val AMARANTH = 0xFFE52B50
    private const val VIRIDIAN = 0xFF40826D
    private const val LOADER_SCRIM = 0xFF0000B3
    private const val HOLO_BLUE_BRIGHT = 0xFF00DDFF
    private const val ON_BACKGROUND_LIGHT = 0xFFA19F9F
    private const val SURFACE_LIGHT = 0xFFE8E4E4
    private const val ON_SURFACE_LIGHT = 0xFF1C1C1C
    private const val ON_SURFACE_SECONDARY_LIGHT = 0xFF424242
    private const val PRIMARY_DARK = 0xFF3DD598
    private const val BACKGROUND_DARK = 0xFF22343C
    private const val ON_BACKGROUND_DARK = 0xFFA19F9F
    private const val SURFACE_DARK = 0xFF30444E
    private const val ON_SURFACE_SECONDARY_DARK = 0xFF96A7AF
    private const val TEXT_GREY = 0xFF8696A0


    val LightGreen = Color(color = LIGHT_GREEN)
    val Amaranth = Color(color = AMARANTH)
    val Viridian = Color(color = VIRIDIAN)
    val LoaderScrim = Color(color = LOADER_SCRIM)
    val HoloBlueBright = Color(color = HOLO_BLUE_BRIGHT)
    val OnBackgroundLight = Color(color = ON_BACKGROUND_LIGHT)
    val SurfaceLight = Color(color = SURFACE_LIGHT)
    val OnSurfaceLight = Color(color = ON_SURFACE_LIGHT)
    val OnSurfaceSecondaryLight = Color(color = ON_SURFACE_SECONDARY_LIGHT)
    val PrimaryDark = Color(color = PRIMARY_DARK)
    val BackgroundDark = Color(color = BACKGROUND_DARK)
    val OnBackgroundDark = Color(color = ON_BACKGROUND_DARK)
    val SurfaceDark = Color(color = SURFACE_DARK)
    val OnSurfaceSecondaryDark = Color(color = ON_SURFACE_SECONDARY_DARK)
    val TextGrey = Color(color = TEXT_GREY)


    @Immutable
    data class Scheme(
        val primary: Color,
        val onPrimary: Color,
        val background: Color,
        val onBackground: Color,
        val surface: Color,
        val onSurface: Color,
        val onSurfaceSecondary: Color
    )

    val LightThemeScheme = Scheme(
        primary = Color.White,
        onPrimary = Color.Black,
        background = Color.White,
        onBackground = OnBackgroundLight,
        surface = SurfaceLight,
        onSurface = OnSurfaceLight,
        onSurfaceSecondary = OnSurfaceSecondaryLight
    )

    val DarkThemeScheme = Scheme(
        primary = PrimaryDark,
        onPrimary = Color.White,
        background = BackgroundDark,
        onBackground = OnBackgroundDark,
        surface = SurfaceDark,
        onSurface = Color.White,
        onSurfaceSecondary = OnSurfaceSecondaryDark
    )
}
