package com.example.tbc_android_2025.extensions

import android.content.Context

fun Int.toPx(context: Context): Int = (this * context.resources.displayMetrics.density).toInt()
