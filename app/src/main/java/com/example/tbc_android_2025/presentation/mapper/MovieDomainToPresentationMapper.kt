package com.example.tbc_android_2025.presentation.mapper

import com.example.tbc_android_2025.domain.model.MovieModel as MovieModelDomain
import com.example.tbc_android_2025.presentation.screen.model.MovieModel as MovieModelPresentation

fun MovieModelDomain.toPresentation() =
    MovieModelPresentation(
        id = id,
        imdbId = imdbId,
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
