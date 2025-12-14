package com.example.tbc_android_2025.domain.mappers

import com.example.tbc_android_2025.domain.models.MovieModel.AgeRating

private const val OLD_CHAR = '-'
private const val NEW_CHAR = '_'
private const val IGNORE_CASE = true

fun String.toAgeRating() = AgeRating.valueOf(
    value = replaceFirst(
        oldChar = OLD_CHAR, newChar = NEW_CHAR, ignoreCase = IGNORE_CASE
    ).uppercase()
)
