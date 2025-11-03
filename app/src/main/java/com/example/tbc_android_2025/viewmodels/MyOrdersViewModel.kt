package com.example.tbc_android_2025.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tbc_android_2025.commons.Colors
import com.example.tbc_android_2025.commons.Images
import com.example.tbc_android_2025.order.Order
import com.example.tbc_android_2025.utils.OrderStatus

class MyOrdersViewModel : ViewModel() {

    private val _orders = MutableLiveData<List<Order>>(emptyList())
    private val _filterStatus = MutableLiveData(OrderStatus.ACTIVE)

    val filterStatus: LiveData<OrderStatus> = _filterStatus
    val filteredOrders: LiveData<List<Order>> = MediatorLiveData<List<Order>>().apply {
        fun updateFiltered() {
            val all = _orders.value ?: emptyList()
            val status = _filterStatus.value ?: OrderStatus.ACTIVE
            value = all.filter { it.status == status }
        }

        addSource(_orders) { updateFiltered() }
        addSource(_filterStatus) { updateFiltered() }
    }


    init { loadInitialOrders() }


    fun setFilter(status: OrderStatus) { _filterStatus.value = status }

    private fun loadInitialOrders() { _orders.value = seed() }

    private fun seed(): List<Order> = listOf(
        Order(
            imageRes = Images.order_1,
            colorRes = Colors.black,
            quantity = 5,
            status = OrderStatus.ACTIVE,
            price = 280
        ),
        Order(
            imageRes = Images.order_2,
            colorRes = Colors.amaranth,
            quantity = 10,
            status = OrderStatus.COMPLETED,
            price = 540
        ),
        Order(
            imageRes = Images.order_3,
            colorRes = Colors.light_green,
            quantity = 5,
            status = OrderStatus.ACTIVE,
            price = 360
        ),
        Order(
            imageRes = Images.order_4,
            colorRes = Colors.purple_500,
            quantity = 5,
            status = OrderStatus.COMPLETED,
            price = 750
        ),
        Order(
            imageRes = Images.order_5,
            colorRes = Colors.brown,
            quantity = 5,
            status = OrderStatus.ACTIVE,
            price = 410
        ),
        Order(
            imageRes = Images.order_6,
            colorRes = Colors.viridian,
            quantity = 5,
            status = OrderStatus.COMPLETED,
            price = 390
        ),
        Order(
            imageRes = Images.order_7,
            colorRes = Colors.white,
            quantity = 5,
            status = OrderStatus.ACTIVE,
            price = 770
        ),
        Order(
            imageRes = Images.order_8,
            colorRes = Colors.black,
            quantity = 5,
            status = OrderStatus.COMPLETED,
            price = 110
        )
    )
}