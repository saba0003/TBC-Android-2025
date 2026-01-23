package com.example.tbc_android_2025.presentation.screen.orders

import com.example.tbc_android_2025.domain.use_case.remote.GetOrdersUseCase
import com.example.tbc_android_2025.presentation.common.BaseViewModel
import com.example.tbc_android_2025.presentation.mapper.toPresentation
import com.example.tbc_android_2025.presentation.screen.orders.OrdersContract.*
import com.example.tbc_android_2025.presentation.screen.orders.OrdersContract.SideEffect.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(private val getOrdersUseCase: GetOrdersUseCase) :
    BaseViewModel<State, Event, SideEffect>(initialState = State.loading()) {

    init {
        onEvent(event = Event.OnFetchOrders)
    }

    override fun onEvent(event: Event) = when (event) {
        Event.OnFetchOrders -> handleResponse(
            apiCall = { getOrdersUseCase() },
            onSuccess = { updateState { copy(orders = it.toPresentation()) } },
            onError = { sendSideEffect(sideEffect = ShowError(error = it)) },
            onLoading = { updateState { copy(isLoading = it.isLoading) } }
        )

        is Event.OnFilterChanged -> updateState { copy(selectedFilter = event.filter) }
        Event.OnDetailsClicked -> emitSideEffect(sideEffect = NavigateToDetails)
    }
}
