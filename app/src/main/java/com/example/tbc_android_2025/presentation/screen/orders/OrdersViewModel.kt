package com.example.tbc_android_2025.presentation.screen.orders

import com.example.tbc_android_2025.domain.use_case.remote.GetOrdersUseCase
import com.example.tbc_android_2025.presentation.common.BaseViewModel
import com.example.tbc_android_2025.presentation.mapper.toPresentation
import com.example.tbc_android_2025.presentation.screen.orders.OrdersContract.*
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
            onError = { sendSideEffect(sideEffect = SideEffect.ShowError(error = it)) },
            onLoading = { updateState { copy(isLoading = it.isLoading) } }
        )

        Event.OnDetailsClicked -> emitSideEffect(sideEffect = SideEffect.NavigateToDetails)
    }
}
