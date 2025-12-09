package com.example.tbc_android_2025.data.mappers

import com.example.tbc_android_2025.data.remote.dtos.MoviesResponseDto
import com.example.tbc_android_2025.domain.models.MovieModel

fun MoviesResponseDto.MovieDto.toDomain() =
    MovieModel(
        id = id,
        title = title,
        description = description,
        releaseDate = releaseDate,
        duration = duration,
        genres = genres,
        languages = languages,
        ageRating = ageRating,
        director = director,
        country = country,
        postersUrls = postersUrls,
        trailersUrls = trailersUrls,
        budget = budget,
        boxOfficeGross = boxOfficeGross
    )

fun MoviesResponseDto.toDomain() = data.map { it.toDomain() }
