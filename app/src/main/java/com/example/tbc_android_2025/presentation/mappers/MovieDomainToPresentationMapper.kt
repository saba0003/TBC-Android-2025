package com.example.tbc_android_2025.presentation.mappers

import com.example.tbc_android_2025.domain.models.MovieModel as MovieModelDomain
import com.example.tbc_android_2025.presentation.screens.commons.MovieModel as MovieModelPresentation

fun MovieModelDomain.toPresentation() =
    MovieModelPresentation(
        id = id,
        title = title,
        description = description,
        releaseDate = releaseDate,
        duration = duration,
        genres = genres.toPresentation(),
        languages = languages.toPresentation(),
        ageRating = ageRating.toPresentation(),
        director = director,
        country = country,
        postersUrls = postersUrls,
        trailersUrls = trailersUrls,
        budget = budget,
        boxOfficeGross = boxOfficeGross
    )

fun List<MovieModelDomain>.toPresentation() = map { it.toPresentation() }
