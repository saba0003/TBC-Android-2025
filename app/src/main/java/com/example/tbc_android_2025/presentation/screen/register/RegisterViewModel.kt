package com.example.tbc_android_2025.presentation.screen.register

import androidx.compose.foundation.text.input.TextFieldState
import com.example.tbc_android_2025.presentation.common.view_model.BaseViewModel
import com.example.tbc_android_2025.presentation.screen.register.RegisterContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() :
    BaseViewModel<State, Event, SideEffect>(initialState = State.loading()) {

    init {
        updateState { copy(isLoading = isLoading.not()) }
    }

    val emailState = TextFieldState()
    val passwordState = TextFieldState()

    override fun onEvent(event: Event) = when (event) {
        Event.OnBackButtonClicked -> emitSideEffect(sideEffect = SideEffect.NavigateToHomeScreen)
        Event.OnNextButtonClicked -> emitSideEffect(sideEffect = SideEffect.NavigateToHomeScreen)
    }
}
