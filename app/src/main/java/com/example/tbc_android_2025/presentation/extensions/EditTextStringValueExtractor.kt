package com.example.tbc_android_2025.presentation.extensions

import android.widget.EditText

fun EditText.asString() = text.toString()

fun EditText.asTrimmedString() = text.trim().toString()
