package com.example.tbc_android_2025.presentation.screen.orders

import com.example.tbc_android_2025.domain.error.AppError
import com.example.tbc_android_2025.presentation.model.OrderModel

interface OrdersContract {
    data class State(
        val orders: List<OrderModel> = emptyList(),
        val selectedFilter: OrderModel.Status = OrderModel.Status.PENDING,
        val isLoading: Boolean = NOT_YET_STARTED
    ) {
        companion object {
            private const val NOT_YET_STARTED = false
            private const val LOADING = true

            fun loading() = State(isLoading = LOADING)
        }
    }

    sealed interface Event {
        data object OnFetchOrders : Event
        data class OnFilterChanged(val filter: OrderModel.Status) : Event
        data object OnDetailsClicked : Event
    }

    sealed interface SideEffect {
        data object NavigateToDetails : SideEffect
        data class ShowError(val error: AppError) : SideEffect
    }
}
