package com.example.tbc_android_2025.presentation.screen.welcome

import com.example.tbc_android_2025.presentation.common.view_model.BaseViewModel
import com.example.tbc_android_2025.presentation.screen.welcome.WelcomeContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor() :
    BaseViewModel<State, Event, SideEffect>(initialState = State.loading()) {

    init {
        updateState { copy(isLoading = isLoading.not()) }
    }

    override fun onEvent(event: Event) = when (event) {
        Event.OnRegisterClick -> emitSideEffect(sideEffect = SideEffect.NavigateToRegister)
        Event.OnLoginClick -> emitSideEffect(sideEffect = SideEffect.NavigateToLogin)
    }
}
