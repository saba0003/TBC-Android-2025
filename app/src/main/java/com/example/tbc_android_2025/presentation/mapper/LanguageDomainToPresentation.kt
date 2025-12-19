package com.example.tbc_android_2025.presentation.mapper

import com.example.tbc_android_2025.domain.model.MovieModel.Language as LanguageDomain
import com.example.tbc_android_2025.presentation.screen.model.MovieModel.Language as LanguagePresentation

fun LanguageDomain.toPresentation() = LanguagePresentation.valueOf(value = name)

fun List<LanguageDomain>.toPresentation() = map { it.toPresentation() }
