package com.example.tbc_android_2025.presentation.mappers

import com.example.tbc_android_2025.domain.models.MovieModel.Language as LanguageDomain
import com.example.tbc_android_2025.presentation.screens.movie_catalogue.MovieModel.Language as LanguagePresentation

fun LanguageDomain.toPresentation() = LanguagePresentation.valueOf(value = name)

fun List<LanguageDomain>.toPresentation() = map { it.toPresentation() }
