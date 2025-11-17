package com.example.tbc_android_2025.data.dtos

import com.google.gson.annotations.SerializedName

data class RemoteUserDto(
    @SerializedName(value = "id") val id: Int,
    @SerializedName(value = "email") val email: String,
    @SerializedName(value = "first_name") val firstName: String,
    @SerializedName(value = "last_name") val lastName: String,
    @SerializedName(value = "avatar") val avatarUrl: String
)
