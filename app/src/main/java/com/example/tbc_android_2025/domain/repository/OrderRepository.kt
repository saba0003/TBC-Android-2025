package com.example.tbc_android_2025.domain.repository

import com.example.tbc_android_2025.domain.common.Resource
import com.example.tbc_android_2025.domain.model.OrderModel
import kotlinx.coroutines.flow.Flow

typealias OrdersListResourceFlow = Flow<Resource<List<OrderModel>>>

interface OrderRepository {
    fun getOrders(): OrdersListResourceFlow
}
