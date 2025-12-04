package com.example.tbc_android_2025.presentation.extensions

import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

fun View.popMessage(@StringRes resId: Int, @ColorRes color: Int? = null) {
    val snackbar =
        Snackbar.make(this, ContextCompat.getString(context, resId), Snackbar.LENGTH_SHORT)
    color?.let { snackbar.setBackgroundTint(ContextCompat.getColor(context, it)) }
    snackbar.show()
}

fun View.popMessage(text: String, @ColorRes color: Int? = null) {
    val snackbar = Snackbar.make(this, text, Snackbar.LENGTH_SHORT)
    color?.let { snackbar.setBackgroundTint(ContextCompat.getColor(context, it)) }
    snackbar.show()
}
