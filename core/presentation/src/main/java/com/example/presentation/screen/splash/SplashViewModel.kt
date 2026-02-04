package com.example.presentation.screen.splash

import com.example.presentation.common.BaseViewModel
import com.example.presentation.screen.splash.SplashContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() :
    BaseViewModel<State, Unit, SideEffect>(initialState = State.loading()) {

    init {
        updateState { copy(isLoading = !isLoading) }
        emitSideEffect(sideEffect = SideEffect.NavigateToOrders)
    }
}
