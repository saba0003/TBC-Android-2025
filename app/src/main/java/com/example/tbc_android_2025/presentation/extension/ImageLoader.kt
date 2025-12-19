package com.example.tbc_android_2025.presentation.extension

import android.widget.ImageView
import coil3.load
import coil3.request.crossfade
import coil3.request.error
import coil3.request.placeholder
import coil3.request.transformations
import coil3.transform.RoundedCornersTransformation
import com.example.tbc_android_2025.common.Images

fun ImageView.loadPoster(url: String?, cornerRadiusDp: Float = 12F) {
    load(data = url) {
        crossfade(enable = true)
        transformations(
            RoundedCornersTransformation(topLeft = cornerRadiusDp, topRight = cornerRadiusDp)
        )
        placeholder(drawableResId = Images.pablo_waiting)
        error(drawableResId = Images.grisha_yelling)
    }
}
