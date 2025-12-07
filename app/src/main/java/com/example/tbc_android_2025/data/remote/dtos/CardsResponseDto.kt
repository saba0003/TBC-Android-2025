package com.example.tbc_android_2025.data.remote.dtos

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.http.Url

@JsonClass(generateAdapter = true)
data class CardsResponseDto(
    val location: String,
    @field:Json(name = ALTITUDE_M) val altitude: Short,
    val title: String,
    @Url val image: String,
    val stars: Byte
) {
    private companion object {
        const val ALTITUDE_M = "altitude_m"
    }
}
