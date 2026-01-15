package com.example.tbc_android_2025.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.tbc_android_2025.presentation.common.Fonts

val FontSizeMedium = 16.sp
val FontSizeLarge = 50.sp

private const val DEFAULT_LINE_HEIGHT = 24
private const val DEFAULT_LETTER_SPACING = 0.5

val ComfortaaFamily = FontFamily(
    Font(
        resId = Fonts.comfortaa,
        weight = FontWeight.Normal
    ),
    Font(
        resId = Fonts.comfortaa,
        weight = FontWeight.Bold
    ),
    Font(
        resId = Fonts.comfortaa,
        weight = FontWeight.Light
    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = FontSizeMedium,
        lineHeight = DEFAULT_LINE_HEIGHT.sp,
        letterSpacing = DEFAULT_LETTER_SPACING.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)
