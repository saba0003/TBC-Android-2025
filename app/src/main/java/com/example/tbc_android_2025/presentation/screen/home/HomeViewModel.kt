package com.example.tbc_android_2025.presentation.screen.home

import com.example.tbc_android_2025.presentation.common.BaseViewModel
import com.example.tbc_android_2025.presentation.screen.home.HomeContract.Event
import com.example.tbc_android_2025.presentation.screen.home.HomeContract.SideEffect
import com.example.tbc_android_2025.presentation.screen.home.HomeContract.State
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() :
    BaseViewModel<State, Event, SideEffect>(initialState = State.loading()) {

    init {
        updateState { copy(isLoading = isLoading.not()) }
    }

    override fun onEvent(event: Event) = when (event) {
        Event.OnLoginButtonClicked -> emitSideEffect(sideEffect = SideEffect.NavigateToLoginScreen)
        Event.OnRegisterButtonClicked -> emitSideEffect(sideEffect = SideEffect.NavigateToRegisterScreen)
    }
}
