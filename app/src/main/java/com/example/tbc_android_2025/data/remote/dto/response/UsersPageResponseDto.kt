package com.example.tbc_android_2025.data.remote.dto.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UsersPageResponseDto(
    val page: Int,
    @property:Json(name = PER_PAGE) val perPage: Int,
    val total: Int,
    @property:Json(name = TOTAL_PAGES) val totalPages: Int,
    val data: List<UserDto>,
    val support: SupportDto,
    @property:Json(name = META) val meta: MetaDto
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
        @property:Json(name = FIRST_NAME) val firstName: String,
        @property:Json(name = LAST_NAME) val lastName: String,
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
        @property:Json(name = POWERED_BY_KEY) val poweredBy: String = POWERED_BY_VALUE,
        @property:Json(name = UPGRADE_URL_KEY) val upgradeUrl: String = UPGRADE_URL_VALUE,
        @property:Json(name = DOCS_URL_KEY) val docsUrl: String = DOCS_URL_VALUE,
        @property:Json(name = EXAMPLE_URL_KEY) val exampleUrl: String = EXAMPLE_URL_VALUE,
        val variant: String = VARIANT_VALUE,
        val message: String = MESSAGE_VALUE,
        val cta: CtaDto,
        val context: String = CONTEXT_VALUE
    ) {
        private companion object {
            const val POWERED_BY_KEY = "powered_by"
            const val POWERED_BY_VALUE = "ReqRes"
            const val DOCS_URL_KEY = "docs_url"
            const val DOCS_URL_VALUE = "https://app.reqres.in/documentation"
            const val UPGRADE_URL_KEY = "upgrade_url"
            const val UPGRADE_URL_VALUE = "https://app.reqres.in/upgrade"
            const val EXAMPLE_URL_KEY = "example_url"
            const val EXAMPLE_URL_VALUE = "https://app.reqres.in/examples/notes-app"
            const val VARIANT_VALUE = "v1_b"
            const val MESSAGE_VALUE =
                "Need more than fake data? Projects give you real CRUD + auth in minutes."
            const val CONTEXT_VALUE = "legacy_success"
        }

        @JsonClass(generateAdapter = true)
        data class CtaDto(val label: String = LABEL_VALUE, val url: String = URL_VALUE) {
            private companion object {
                const val LABEL_VALUE = "Get started"
                const val URL_VALUE = "https://app.reqres.in/upgrade"
            }
        }
    }
}
