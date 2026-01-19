package com.example.tbc_android_2025.presentation.extension

fun <T> MutableList<T>.resetTo(route: T) {
    clear()
    add(element = route)
}
