package com.example.tbc_android_2025.presentation.mapper

import com.example.tbc_android_2025.domain.model.OrderModel as OrderModelDomain
import com.example.tbc_android_2025.presentation.model.OrderModel as OrderModelPresentation

fun OrderModelDomain.toPresentation() = OrderModelPresentation(
    id = id,
    orderNumber = orderNumber,
    date = date,
    trackingNumber = trackingNumber,
    quantity = quantity,
    subtotal = subtotal,
    status = OrderModelPresentation.Status.valueOf(value = status.name)
)

fun List<OrderModelDomain>.toPresentation() = map { it.toPresentation() }
