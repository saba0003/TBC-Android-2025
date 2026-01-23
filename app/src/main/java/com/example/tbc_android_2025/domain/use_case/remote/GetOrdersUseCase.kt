package com.example.tbc_android_2025.domain.use_case.remote

import com.example.tbc_android_2025.domain.repository.OrderRepository
import com.example.tbc_android_2025.domain.repository.OrdersListResourceFlow
import javax.inject.Inject

class GetOrdersUseCase @Inject constructor(private val orderRepository: OrderRepository) {

    operator fun invoke(): OrdersListResourceFlow = orderRepository.getOrders()

}
