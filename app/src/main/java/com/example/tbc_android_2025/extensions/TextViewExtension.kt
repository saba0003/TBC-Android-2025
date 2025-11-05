package com.example.tbc_android_2025.extensions


import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout


fun TextView.updateConstraints(update: ConstraintLayout.LayoutParams.() -> Unit) {
    layoutParams = (layoutParams as ConstraintLayout.LayoutParams)
        .apply(block = update).also { layoutParams = it }
}
