package com.example.tbc_android_2025.data.mappers

import com.example.tbc_android_2025.data.remote.dtos.MoviesResponseDto
import com.example.tbc_android_2025.data.remote.dtos.MoviesResponseDto.MovieDto
import com.example.tbc_android_2025.domain.mappers.*
import com.example.tbc_android_2025.domain.models.MovieModel

fun MovieDto.toDomain() =
    MovieModel(
        id = id,
        title = title,
        description = description,
        releaseDate = releaseDate.toLocalDate(),
        duration = duration,
        genres = genres.toGenres(),
        languages = languages.toLanguages(),
        ageRating = ageRating.toAgeRating(),
        director = director,
        country = country,
        postersUrls = postersUrls,
        trailersUrls = trailersUrls,
        budget = budget,
        boxOfficeGross = boxOfficeGross
    )

fun MoviesResponseDto.toDomain() = data.map { it.toDomain() }
