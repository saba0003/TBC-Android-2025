package com.example.tbc_android_2025.presentation.commons

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<STATE, EVENT, SIDE_EFFECT>() : ViewModel() {

    protected abstract val initialState: STATE

    protected val _state = MutableStateFlow(value = initialState)
    val state = _state.asStateFlow()

    private val _sideEffect = MutableSharedFlow<SIDE_EFFECT>()
    val sideEffect = _sideEffect.asSharedFlow()


    protected fun updateState(reducer: STATE.() -> STATE) = _state.update { it.reducer() }

    abstract fun onEvent(event: EVENT)

    protected suspend fun sendEffect(sideEffect: SIDE_EFFECT) = _sideEffect.emit(value = sideEffect)
}
