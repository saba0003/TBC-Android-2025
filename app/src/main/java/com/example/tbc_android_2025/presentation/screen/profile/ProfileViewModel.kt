package com.example.tbc_android_2025.presentation.screen.profile

import com.example.tbc_android_2025.presentation.common.BaseViewModel
import com.example.tbc_android_2025.presentation.screen.profile.ProfileContract.*

class ProfileViewModel :
    BaseViewModel<State, Event, SideEffect>(initialState = State.loading()) {

    init {
        updateState { copy(isLoading = isLoading.not()) }
    }

    override fun onEvent(event: Event) = when (event) {
        Event.OnHomeClick -> emitSideEffect(sideEffect = SideEffect.NavigateToHome)
        Event.OnLogoutClick -> emitSideEffect(sideEffect = SideEffect.NavigateToWelcome)
    }
}
