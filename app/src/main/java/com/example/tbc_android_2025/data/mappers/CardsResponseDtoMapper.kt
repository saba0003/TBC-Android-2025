package com.example.tbc_android_2025.data.mappers

import com.example.tbc_android_2025.data.remote.dtos.CardsResponseDto
import com.example.tbc_android_2025.domain.models.CardModel

fun CardsResponseDto.toDomain() =
    CardModel(
        location = location,
        altitude = altitude,
        title = title,
        image = image,
        stars = stars
    )

fun List<CardsResponseDto>.toDomain() = map { it.toDomain() }
