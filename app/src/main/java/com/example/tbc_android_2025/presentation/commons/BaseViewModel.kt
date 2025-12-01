package com.example.tbc_android_2025.presentation.commons

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<S, E>(initialState: S) : ViewModel() {

    protected val _state = MutableStateFlow(value = initialState)
    val state = _state.asStateFlow()


    protected fun updateState(reducer: S.() -> S) {
        _state.update { it.reducer() }
    }


    abstract fun onEvent(event: E)
}
