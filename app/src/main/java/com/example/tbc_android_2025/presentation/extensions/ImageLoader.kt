package com.example.tbc_android_2025.presentation.extensions

import android.widget.ImageView
import coil3.load
import coil3.request.crossfade
import coil3.request.error
import coil3.request.placeholder
import coil3.request.transformations
import coil3.transform.CircleCropTransformation
import com.example.tbc_android_2025.commons.Drawables

fun ImageView.loadImage(url: String?) {
    this.load(data = url) {
        crossfade(enable = true)
        transformations(CircleCropTransformation())
        placeholder(drawableResId = Drawables.ic_launcher_background)
        error(drawableResId = Drawables.ic_launcher_foreground)
    }
}
