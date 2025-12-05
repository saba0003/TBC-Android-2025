package com.example.tbc_android_2025.data.remote.dtos

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponseDto(
    val id: Int,
    @field:Json(name = FULL_NAME) val fullName: String,
    val email: String,
    @field:Json(name = ACTIVATION_STATUS) val activationStatus: Int,
    @field:Json(name = LAST_ACTIVE_DESCRIPTION) val lastActiveDescription: String,
    @field:Json(name = LAST_ACTIVE_EPOCH) val lastActiveEpoch: Int,
    @field:Json(name = PROFILE_IMAGE_URL) val profileImageUrl: String?
) {
    private companion object {
        const val FULL_NAME = "full_name"
        const val ACTIVATION_STATUS = "activation_status"
        const val LAST_ACTIVE_DESCRIPTION = "last_active_description"
        const val LAST_ACTIVE_EPOCH = "last_active_epoch"
        const val PROFILE_IMAGE_URL = "profile_image_url"
    }
}
