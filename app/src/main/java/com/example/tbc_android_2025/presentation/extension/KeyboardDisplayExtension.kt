package com.example.tbc_android_2025.presentation.extension

import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService

fun View.showKeyboard() = post {
    requestFocus()
    context.getSystemService<InputMethodManager>()
        ?.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun View.hideKeyboard() = windowToken?.let {
    context.getSystemService<InputMethodManager>()?.hideSoftInputFromWindow(it, 0)
}
