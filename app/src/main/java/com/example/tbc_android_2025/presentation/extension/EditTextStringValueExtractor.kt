package com.example.tbc_android_2025.presentation.extension

import android.widget.EditText

fun EditText.asString() = text.toString()

fun EditText.asTrimmedString() = text.trim().toString()
