package com.example.tbc_android_2025.presentation.extension

import kotlinx.datetime.LocalDate
import kotlinx.datetime.number

private const val DD_MM_YYYY = "%02d–%02d–%04d"

fun LocalDate.format() = DD_MM_YYYY.format(day, month.number, year)
