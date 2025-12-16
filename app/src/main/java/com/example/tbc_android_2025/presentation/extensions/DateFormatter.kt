package com.example.tbc_android_2025.presentation.extensions

import kotlinx.datetime.LocalDate
import kotlinx.datetime.number

// TODO: hardcoded string
fun LocalDate.format() = "%02d–%02d–%04d".format(day, month.number, year)
