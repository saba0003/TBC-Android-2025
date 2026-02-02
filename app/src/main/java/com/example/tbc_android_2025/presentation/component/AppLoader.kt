package com.example.tbc_android_2025.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tbc_android_2025.design.global.AppColors

private const val STROKE_WIDTH = 4
private const val LOADING = true
private const val BLOCK_USER_INPUT = true
private const val SHOW_BACKGROUND = true

@Composable
fun AppLoader(
    modifier: Modifier = Modifier,
    isLoading: Boolean = LOADING,
    scrimColor: Color = AppColors.LoaderScrim,
    indicatorColor: Color = AppColors.HoloBlueBright
) = AnimatedVisibility(visible = isLoading, enter = fadeIn(), exit = fadeOut()) {
    if (isLoading)
        LoaderOverlay(modifier = modifier, scrimColor = scrimColor, indicatorColor = indicatorColor)
}

@Composable
private fun LoaderOverlay(
    modifier: Modifier, scrimColor: Color, indicatorColor: Color
) = Box(
    modifier = modifier
        .fillMaxSize()
        .background(color = scrimColor)
        .blockUserInput(enabled = BLOCK_USER_INPUT),
    contentAlignment = Alignment.Center
) { CircularProgressIndicator(color = indicatorColor, strokeWidth = STROKE_WIDTH.dp) }

@Composable
private fun Modifier.blockUserInput(enabled: Boolean): Modifier =
    if (enabled) pointerInput(key1 = Unit) { detectTapGestures { } } else this

@Composable
@Preview(showBackground = SHOW_BACKGROUND)
private fun AppLoaderPreview() = AppLoader()
