package com.example.tbc_android_2025.presentation.mapper

import com.example.tbc_android_2025.domain.model.MovieModel.Genre as GenreDomain
import com.example.tbc_android_2025.presentation.screen.model.MovieModel.Genre as GenrePresentation

fun GenreDomain.toPresentation() = GenrePresentation.valueOf(value = name)

fun List<GenreDomain>.toPresentation() = map { it.toPresentation() }
