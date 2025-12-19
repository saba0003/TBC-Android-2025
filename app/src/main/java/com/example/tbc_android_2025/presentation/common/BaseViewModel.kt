package com.example.tbc_android_2025.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATE, EVENT, SIDE_EFFECT>(initialState: STATE) : ViewModel() {

    private val _state = MutableStateFlow(value = initialState)
    val state = _state.asStateFlow()

    private val _sideEffect = MutableSharedFlow<SIDE_EFFECT>()
    val sideEffect = _sideEffect.asSharedFlow()


    open fun onEvent(event: EVENT) {}

    protected fun updateState(reducer: STATE.() -> STATE) = _state.update { it.reducer() }

    protected fun sendSideEffect(sideEffect: SIDE_EFFECT) {
        viewModelScope.launch { _sideEffect.emit(value = sideEffect) }
    }

    protected suspend fun emitSideEffect(sideEffect: SIDE_EFFECT) {
        _sideEffect.emit(value = sideEffect)
    }
}
