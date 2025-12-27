package com.example.tbc_android_2025.presentation.extension

import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

fun View.popMessage(
    text: String,
    @ColorRes color: Int? = null,
    duration: Int = Snackbar.LENGTH_SHORT
) {
    val snackbar = Snackbar.make(this, text, duration)
    color?.let { snackbar.setBackgroundTint(ContextCompat.getColor(context, it)) }
    snackbar.show()
}

fun View.popMessage(
    @StringRes resId: Int,
    @ColorRes color: Int? = null,
    duration: Int = Snackbar.LENGTH_SHORT
) = popMessage(text = ContextCompat.getString(context, resId), color = color, duration = duration)
