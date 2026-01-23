package com.example.tbc_android_2025.data.repository

import com.example.tbc_android_2025.data.remote.common.ResponseHandler
import com.example.tbc_android_2025.data.remote.mapper.asResource
import com.example.tbc_android_2025.data.remote.mapper.toDomain
import com.example.tbc_android_2025.data.remote.service.OrderFetchService
import com.example.tbc_android_2025.domain.repository.OrderRepository
import com.example.tbc_android_2025.domain.repository.OrdersListResourceFlow
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val orderFetchService: OrderFetchService, private val responseHandler: ResponseHandler
) : OrderRepository {

    override fun getOrders(): OrdersListResourceFlow =
        responseHandler.safeApiCall { orderFetchService.getOrders() }.asResource { it.toDomain() }

}
