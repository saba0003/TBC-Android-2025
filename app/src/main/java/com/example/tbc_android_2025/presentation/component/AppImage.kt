package com.example.tbc_android_2025.presentation.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.tbc_android_2025.presentation.common.Drawables

private const val ENABLE_CROSSFADE = true

@Composable
fun AppImage(
    url: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    @DrawableRes placeholderRes: Int = Drawables.ic_launcher_background,
    @DrawableRes errorRes: Int = Drawables.ic_launcher_foreground
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(data = url)
            .crossfade(enable = ENABLE_CROSSFADE)
            .placeholder(drawableResId = placeholderRes)
            .error(drawableResId = errorRes)
            .build()
    )

    Image(
        painter = painter,
        contentDescription = null,
        modifier = modifier,
        contentScale = contentScale
    )
}
