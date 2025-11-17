package com.example.tbc_android_2025.data.dtos

import com.google.gson.annotations.SerializedName

data class UsersResponseDto(
    @SerializedName(value = "page") val page: Int,
    @SerializedName(value = "per_page") val perPage: Int,
    @SerializedName(value = "total") val total: Int,
    @SerializedName(value = "total_pages") val totalPages: Int,
    @SerializedName(value = "data") val data: List<RemoteUserDto>
)
