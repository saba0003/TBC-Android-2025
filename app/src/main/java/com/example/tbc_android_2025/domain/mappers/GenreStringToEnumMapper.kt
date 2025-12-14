package com.example.tbc_android_2025.domain.mappers

import com.example.tbc_android_2025.domain.models.MovieModel.Genre

private const val SCIENCE_FICTION = "Sci-Fi"
private const val GRAPHIC_NOVEL = "Graphic Novel"
private const val DETECTIVE_FICTION = "Detective Fiction"

fun String.toGenre() = when (this) {
    SCIENCE_FICTION -> Genre.SCIENCE_FICTION
    GRAPHIC_NOVEL -> Genre.GRAPHIC_NOVEL
    DETECTIVE_FICTION -> Genre.DETECTIVE_FICTION
    else -> Genre.valueOf(value = uppercase())
}

fun List<String>.toGenres() = map { it.toGenre() }
