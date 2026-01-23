package com.example.tbc_android_2025.data.remote.dto

import com.example.tbc_android_2025.data.remote.dto.OrderDto.Companion.GENERATE_ADAPTER
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = GENERATE_ADAPTER)
data class OrderDto(
    val id: Int,
    @property:Json(name = ORDER_NUMBER) val orderNumber: String,
    val date: String,
    @property:Json(name = TRACKING_NUMBER) val trackingNumber: String,
    val quantity: Int,
    val subtotal: Int,
    val status: String
) {
    private companion object {
        const val GENERATE_ADAPTER = true
        const val ORDER_NUMBER = "order_number"
        const val TRACKING_NUMBER = "tracking_number"
    }
}
