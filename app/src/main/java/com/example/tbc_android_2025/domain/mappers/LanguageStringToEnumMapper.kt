package com.example.tbc_android_2025.domain.mappers

import com.example.tbc_android_2025.domain.models.MovieModel.Language

fun String.toLanguage() = Language.valueOf(value = uppercase())

fun List<String>.toLanguages() = map { it.toLanguage() }
