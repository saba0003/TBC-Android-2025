package com.example.tbc_android_2025.data.remote.service

import com.example.tbc_android_2025.data.remote.dto.OrderDto
import retrofit2.Response
import retrofit2.http.GET

interface OrderFetchService {

    @GET(value = ORDERS_ENDPOINT)
    suspend fun getOrders(): Response<List<OrderDto>>

    private companion object {
        const val ORDERS_ENDPOINT = "orders"
    }
}
