package com.example.tbc_android_2025.order

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.example.tbc_android_2025.commons.OrderStatus

data class Order(
    val id: Long = System.currentTimeMillis(),
    @param:DrawableRes val imageRes: Int,
    @param:ColorRes val colorRes: Int,
    val quantity: Int,
    val status: OrderStatus,
    val price: Int,
    var leaveReviewButtonIsVisible: Boolean = false
)
