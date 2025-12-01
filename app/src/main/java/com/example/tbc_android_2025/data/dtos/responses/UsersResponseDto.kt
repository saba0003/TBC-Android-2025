package com.example.tbc_android_2025.data.dtos.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UsersResponseDto(
    val page: Int,
    @field:Json(name = PER_PAGE) val perPage: Int,
    val total: Int,
    @field:Json(name = TOTAL_PAGES) val totalPages: Int,
    val data: List<UserDto>,
    val support: SupportDto,
    @field:Json(name = META) val meta: MetaDto
) {
    private companion object {
        const val PER_PAGE = "per_page"
        const val TOTAL_PAGES = "total_pages"
        const val META = "_meta"
    }

    @JsonClass(generateAdapter = true)
    data class UserDto(
        val id: Int,
        val email: String,
        @field:Json(name = FIRST_NAME) val firstName: String,
        @field:Json(name = LAST_NAME) val lastName: String,
        val avatar: String
    ) {
        private companion object {
            const val FIRST_NAME = "first_name"
            const val LAST_NAME = "last_name"
        }
    }

    @JsonClass(generateAdapter = true)
    data class SupportDto(val url: String = URL, val text: String = TEXT) {
        private companion object {
            const val URL =
                "https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral"
            const val TEXT =
                "Tired of writing endless social media content? Let Content Caddy generate it for you."
        }
    }

    @JsonClass(generateAdapter = true)
    data class MetaDto(
        @field:Json(name = POWERED_BY_KEY) val poweredBy: String = POWERED_BY_VALUE,
        @field:Json(name = UPGRADE_URL_KEY) val upgradeUrl: String = UPGRADE_URL_VALUE,
        @field:Json(name = DOCS_URL_KEY) val docsUrl: String = DOCS_URL_VALUE,
        @field:Json(name = TEMPLATE_GALLERY_KEY) val templateGallery: String = TEMPLATE_GALLERY_VALUE,
        val message: String = MESSAGE_VALUE,
        val features: List<String> = listOf(FEATURE1, FEATURE2, FEATURE3, FEATURE4),
        @field:Json(name = UPGRADE_CTA_KEY) val upgradeCta: String = UPGRADE_CTA_VALUE,
    ) {
        private companion object {
            const val POWERED_BY_KEY = "powered_by"
            const val POWERED_BY_VALUE = "\uD83D\uDE80 ReqRes - Deploy backends in 30 seconds"
            const val UPGRADE_URL_KEY = "upgrade_url"
            const val UPGRADE_URL_VALUE = "https://app.reqres.in/upgrade"
            const val DOCS_URL_KEY = "docs_url"
            const val DOCS_URL_VALUE = "https://reqres.in"
            const val TEMPLATE_GALLERY_KEY = "template_gallery"
            const val TEMPLATE_GALLERY_VALUE = "https://app.reqres.in/templates"
            const val MESSAGE_VALUE =
                "This API is powered by ReqRes. Deploy your own backend in 30 seconds!"
            const val FEATURE1 = "30 Second Backend Templates"
            const val FEATURE2 = "Custom API Endpoints"
            const val FEATURE3 = "Data Persistence"
            const val FEATURE4 = "Real-time Analytics"
            const val UPGRADE_CTA_KEY = "upgrade_cta"
            const val UPGRADE_CTA_VALUE =
                "Upgrade to Pro for unlimited requests, custom endpoints, and data persistence"
        }
    }
}
