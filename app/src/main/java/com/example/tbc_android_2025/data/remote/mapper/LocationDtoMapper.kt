package com.example.tbc_android_2025.data.remote.mapper

import com.example.tbc_android_2025.data.remote.dto.LocationDto
import com.example.tbc_android_2025.domain.model.LocationModel

fun LocationDto.toDomain(): LocationModel = LocationModel(
    id = id,
    title = title,
    location = location,
    number = number,
    photo = photo,
    price = price,
    stars = stars
)

fun List<LocationDto>.toDomain(): List<LocationModel> = map { it.toDomain() }
