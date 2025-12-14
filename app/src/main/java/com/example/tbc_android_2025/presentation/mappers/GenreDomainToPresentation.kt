package com.example.tbc_android_2025.presentation.mappers

import com.example.tbc_android_2025.domain.models.MovieModel.Genre as GenreDomain
import com.example.tbc_android_2025.presentation.screens.movie_catalogue.MovieModel.Genre as GenrePresentation

fun GenreDomain.toPresentation() = GenrePresentation.valueOf(value = name)

fun List<GenreDomain>.toPresentation() = map { it.toPresentation() }
