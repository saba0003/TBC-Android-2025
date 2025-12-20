package com.example.tbc_android_2025.domain.mapper

import kotlinx.datetime.LocalDate

private const val DATE_DELIMITER = '-'

fun String.toLocalDate(): LocalDate {
    val (day, month, year) = split(DATE_DELIMITER)
    return LocalDate(year = year.toInt(), month = month.toInt(), day = day.toInt())
}
