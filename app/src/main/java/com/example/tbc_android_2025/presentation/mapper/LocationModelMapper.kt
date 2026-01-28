package com.example.tbc_android_2025.presentation.mapper

import com.example.tbc_android_2025.domain.model.LocationModel as LocationModelDomain
import com.example.tbc_android_2025.presentation.model.LocationModel as LocationModelPresentation

fun LocationModelDomain.toPresentation() = LocationModelPresentation(
    id = id,
    title = title,
    location = location,
    number = number,
    photo = photo,
    price = price,
    stars = stars
)

fun List<LocationModelDomain>.toPresentation() = map { it.toPresentation() }
