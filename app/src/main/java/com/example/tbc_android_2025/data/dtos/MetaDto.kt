package com.example.tbc_android_2025.data.dtos

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MetaDto(
    @field:Json(name = "powered_by") val poweredBy: String = POWERED_BY,
    @field:Json(name = "upgrade_url") val upgradeUrl: String = UPGRADE_URL,
    @field:Json(name = "docs_url") val docsUrl: String = DOCS_URL,
    @field:Json(name = "docs_url") val templateGallery: String = TEMPLATE_GALLERY,
    val message: String = MESSAGE,
    val features: List<String> = listOf(FEATURE1, FEATURE2, FEATURE3, FEATURE4),
    @field:Json(name = "upgrade_cta") val upgradeCta: String = UPGRADE_CTA,
) {
    private companion object {
        const val POWERED_BY = "\uD83D\uDE80 ReqRes - Deploy backends in 30 seconds"
        const val UPGRADE_URL = "https://app.reqres.in/upgrade"
        const val DOCS_URL = "https://reqres.in"
        const val TEMPLATE_GALLERY = "https://app.reqres.in/templates"
        const val MESSAGE = "This API is powered by ReqRes. Deploy your own backend in 30 seconds!"
        const val FEATURE1 = "30 Second Backend Templates"
        const val FEATURE2 = "Custom API Endpoints"
        const val FEATURE3 = "Data Persistence"
        const val FEATURE4 = "Real-time Analytics"
        const val UPGRADE_CTA = "Upgrade to Pro for unlimited requests, custom endpoints, and data persistence"
    }
}
