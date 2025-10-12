package com.example.tbc_android_2025

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.popMessage(resId: Int, duration: Int = Snackbar.LENGTH_SHORT) =
    Snackbar.make(this, this.resources.getText(resId), duration).show()

fun View.popMessage(text: String, duration: Int = Snackbar.LENGTH_SHORT) =
    Snackbar.make(this, text, duration).show()
