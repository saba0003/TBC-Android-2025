package com.example.tbc_android_2025.presentation.model

import com.example.tbc_android_2025.presentation.common.Strings

data class OrderModel(
    val id: Int,
    val orderNumber: String,
    val date: String,
    val trackingNumber: String,
    val quantity: Int,
    val subtotal: Int,
    val status: Status
) {
    enum class Status(val stringResId: Int) {
        PENDING(stringResId = Strings.pending),
        DELIVERED(stringResId = Strings.delivered),
        CANCELED(stringResId = Strings.canceled)
    }
}
