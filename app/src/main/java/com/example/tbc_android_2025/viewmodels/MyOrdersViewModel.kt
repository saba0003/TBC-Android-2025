package com.example.tbc_android_2025.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbc_android_2025.commons.Colors
import com.example.tbc_android_2025.commons.Images
import com.example.tbc_android_2025.order.Order
import com.example.tbc_android_2025.commons.OrderStatus
import com.example.tbc_android_2025.commons.OrderStatus.ACTIVE
import com.example.tbc_android_2025.commons.OrderStatus.COMPLETED
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

typealias Orders = List<Order>
typealias OrdersStateFlow = StateFlow<Orders>

class MyOrdersViewModel : ViewModel() {

    private val _orders = MutableStateFlow<Orders>(value = emptyList())
    private val _filterStatus = MutableStateFlow(value = ACTIVE)

    val filterStatus: StateFlow<OrderStatus> = _filterStatus.asStateFlow()
    val filteredOrders: OrdersStateFlow =
        combine(flow = _orders, flow2 = _filterStatus) { allOrders, status ->
            allOrders.filter { it.status == status }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
            initialValue = emptyList()
        )


    init { loadInitialOrders() }


    fun setFilter(status: OrderStatus) { _filterStatus.value = status }


    /** ======================================== AUX ============================================ */
    private fun loadInitialOrders() { _orders.value = seed() }

    private fun seed(): List<Order> = listOf(
        Order(
            imageRes = Images.order_1,
            colorRes = Colors.black,
            quantity = 6,
            status = ACTIVE,
            price = 280
        ),
        Order(
            imageRes = Images.order_2,
            colorRes = Colors.amaranth,
            quantity = 54,
            status = COMPLETED,
            price = 540
        ),
        Order(
            imageRes = Images.order_3,
            colorRes = Colors.light_green,
            quantity = 21,
            status = ACTIVE,
            price = 360
        ),
        Order(
            imageRes = Images.order_4,
            colorRes = Colors.purple_500,
            quantity = 5334,
            status = COMPLETED,
            price = 750
        ),
        Order(
            imageRes = Images.order_5,
            colorRes = Colors.brown,
            quantity = 414,
            status = ACTIVE,
            price = 410
        ),
        Order(
            imageRes = Images.order_6,
            colorRes = Colors.viridian,
            quantity = 144,
            status = COMPLETED,
            price = 390
        ),
        Order(
            imageRes = Images.order_7,
            colorRes = Colors.white,
            quantity = 245,
            status = ACTIVE,
            price = 770
        ),
        Order(
            imageRes = Images.order_8,
            colorRes = Colors.black,
            quantity = 10,
            status = COMPLETED,
            price = 110
        )
    )
    /** ========================================================================================= */
}
