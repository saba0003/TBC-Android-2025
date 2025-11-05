package com.example.tbc_android_2025.extensions


import android.content.res.Resources


fun Int.isEven(): Boolean = this % 2 == 0
fun Int.isOdd(): Boolean = this % 2 != 0
fun Int.dpToPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()
