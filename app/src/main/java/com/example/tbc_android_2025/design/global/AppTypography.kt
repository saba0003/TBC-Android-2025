package com.example.tbc_android_2025.design.global

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.tbc_android_2025.presentation.common.Fonts

object AppTypography {
    private const val FONT_SIZE_MEDIUM = 16
    private const val FONT_SIZE_LARGE = 50

    private const val BODY_LINE_HEIGHT = 24
    private const val BODY_LETTER_SPACING = 0.5

    private const val DISPLAY_LINE_HEIGHT = 64
    private const val DISPLAY_LETTER_SPACING = 0

    private val FontSizeMedium = FONT_SIZE_MEDIUM.sp
    private val FontSizeLarge = FONT_SIZE_LARGE.sp


    private val ComfortaaFamily = FontFamily(
        Font(resId = Fonts.comfortaa, weight = FontWeight.Normal),
        Font(resId = Fonts.comfortaa, weight = FontWeight.Bold),
        Font(resId = Fonts.comfortaa, weight = FontWeight.Light)
    )

    val Typography = Typography(
        bodyMedium = TextStyle(
            fontFamily = ComfortaaFamily,
            fontWeight = FontWeight.Normal,
            fontSize = FontSizeMedium,
            lineHeight = BODY_LINE_HEIGHT.sp,
            letterSpacing = BODY_LETTER_SPACING.sp
        ),
        displayLarge = TextStyle(
            fontFamily = ComfortaaFamily,
            fontWeight = FontWeight.Bold,
            fontSize = FontSizeLarge,
            lineHeight = DISPLAY_LINE_HEIGHT.sp,
            letterSpacing = DISPLAY_LETTER_SPACING.sp
        )
    )
}
