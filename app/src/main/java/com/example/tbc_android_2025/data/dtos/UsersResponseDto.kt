package com.example.tbc_android_2025.data.dtos

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UsersResponseDto(
    val page: Int,
    @field:Json(name = "per_page") val perPage: Int,
    val total: Int,
    @field:Json(name = "total_pages") val totalPages: Int,
    val data: List<UserDto>,
    val support: SupportDto,
    @field:Json(name = "_meta") val meta: MetaDto
)
