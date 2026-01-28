package com.example.tbc_android_2025.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.tbc_android_2025.presentation.ui.theme.DesignTokens

private const val MAX_STARS = 5
private const val SAMPLE_RATING = 3
private const val SHOW_BACKGROUND = true

@Composable
fun AppRatingBar(
    rating: Int,
    filledColor: Color = DesignTokens.Colors.RatingBar.Filled,
    unfilledColor: Color = DesignTokens.Colors.RatingBar.Unfilled
) = Row(modifier = Modifier.wrapContentSize()) {
    repeat(times = MAX_STARS) {
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = null,
            tint = if (it < rating) filledColor else unfilledColor,
            modifier = Modifier.size(size = DesignTokens.Dimensions.AppRatingBar.Size)
        )
    }
}

@Composable
@Preview(showBackground = SHOW_BACKGROUND)
private fun AppRatingBarPreview() = AppRatingBar(
    rating = SAMPLE_RATING,
    filledColor = DesignTokens.Colors.Viridian,
    unfilledColor = DesignTokens.Colors.Amaranth
)
