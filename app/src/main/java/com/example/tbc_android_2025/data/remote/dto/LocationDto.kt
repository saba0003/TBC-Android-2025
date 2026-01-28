package com.example.tbc_android_2025.data.remote.dto

import com.example.tbc_android_2025.data.remote.dto.LocationDto.Companion.GENERATE_ADAPTER
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = GENERATE_ADAPTER)
data class LocationDto(
    val id: Int,
    val title: String,
    val location: String,
    val number: String,
    val photo: String,
    val price: Int,
    val stars: Int
) {
    private companion object {
        const val GENERATE_ADAPTER = true
    }
}