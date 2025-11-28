package com.example.tbc_android_2025.data.dtos

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SupportDto(val url: String = URL, val text: String = TEXT) {
    private companion object {
        const val URL = "https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral"
        const val TEXT = "Tired of writing endless social media content? Let Content Caddy generate it for you."
    }
}
