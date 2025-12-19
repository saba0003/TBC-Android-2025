package com.example.tbc_android_2025.domain.mapper

import com.example.tbc_android_2025.domain.model.MovieModel.Language

fun String.toLanguage() = Language.valueOf(value = uppercase())

fun List<String>.toLanguages() = map { it.toLanguage() }
