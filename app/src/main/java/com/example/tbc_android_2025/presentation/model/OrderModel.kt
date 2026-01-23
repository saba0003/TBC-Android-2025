package com.example.tbc_android_2025.presentation.model

data class OrderModel(
    val id: Int,
    val orderNumber: String,
    val date: String,
    val trackingNumber: String,
    val quantity: Int,
    val subtotal: Int,
    val status: Status
) {
    enum class Status { PENDING, DELIVERED, CANCELED }
}
