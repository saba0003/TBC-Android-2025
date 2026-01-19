package com.example.tbc_android_2025.presentation.screen.login

import com.example.tbc_android_2025.presentation.common.BaseViewModel
import com.example.tbc_android_2025.presentation.screen.login.LoginContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() :
    BaseViewModel<State, Event, SideEffect>(initialState = State.loading()) {

    init {
        updateState { copy(isLoading = isLoading.not()) }
    }

    override fun onEvent(event: Event) = when (event) {
        Event.OnBackButtonClicked -> emitSideEffect(sideEffect = SideEffect.NavigateToHomeScreen)
        Event.OnLoginButtonClicked -> emitSideEffect(sideEffect = SideEffect.NavigateToHomeScreen)
    }
}
