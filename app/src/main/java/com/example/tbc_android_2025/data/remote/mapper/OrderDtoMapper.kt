package com.example.tbc_android_2025.data.remote.mapper

import com.example.tbc_android_2025.data.remote.dto.OrderDto
import com.example.tbc_android_2025.domain.model.OrderModel

fun OrderDto.toDomain(): OrderModel = OrderModel(
    id = id,
    orderNumber = orderNumber,
    date = date,
    trackingNumber = trackingNumber,
    quantity = quantity,
    subtotal = subtotal,
    status = OrderModel.Status.valueOf(value = status)
)

fun List<OrderDto>.toDomain(): List<OrderModel> = map { it.toDomain() }
