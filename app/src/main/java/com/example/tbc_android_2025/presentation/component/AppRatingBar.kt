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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tbc_android_2025.design.global.AppColors
import com.example.tbc_android_2025.design.misc.RatingBarColors
import com.example.tbc_android_2025.presentation.common.Strings
import com.example.tbc_android_2025.presentation.ui.theme.TBCAndroid2025Theme

private const val MAX_STARS = 5
private const val STAR_SIZE = 16
private const val SAMPLE_RATING = 3
private const val SHOW_BACKGROUND = true
private const val IS_IN_LIGHT_MODE = false
private const val IS_IN_DARK_MODE = true
private const val LIGHT_MODE = "Light Mode"
private const val DARK_MODE = "Dark Mode"

@Composable
fun AppRatingBar(
    rating: Int,
    filledColor: Color = RatingBarColors.Filled,
    unfilledColor: Color = RatingBarColors.Unfilled
) = Row(modifier = Modifier.wrapContentSize()) {
    repeat(times = MAX_STARS) {
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = stringResource(id = Strings.five_star_rating_bar),
            tint = if (it < rating) filledColor else unfilledColor,
            modifier = Modifier.size(size = STAR_SIZE.dp)
        )
    }
}

@Composable
@Preview(name = LIGHT_MODE, showBackground = SHOW_BACKGROUND)
private fun AppRatingBarLightModePreview() = TBCAndroid2025Theme(darkTheme = IS_IN_LIGHT_MODE) {
    AppRatingBar(
        rating = SAMPLE_RATING,
        filledColor = AppColors.Viridian,
        unfilledColor = AppColors.Amaranth
    )
}

@Composable
@Preview(name = DARK_MODE, showBackground = SHOW_BACKGROUND)
private fun AppRatingBarDARKModePreview() = TBCAndroid2025Theme(darkTheme = IS_IN_DARK_MODE) {
    AppRatingBar(
        rating = SAMPLE_RATING,
        filledColor = AppColors.Viridian,
        unfilledColor = AppColors.Amaranth
    )
}
