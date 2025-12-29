package com.example.tbc_android_2025.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbc_android_2025.domain.common.Resource
import com.example.tbc_android_2025.domain.error.AppError
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATE, EVENT, SIDE_EFFECT>(initialState: STATE) : ViewModel() {

    private val _state = MutableStateFlow(value = initialState)
    val state = _state.asStateFlow()

    private val _sideEffect = Channel<SIDE_EFFECT>(capacity = Channel.CONFLATED)
    val sideEffect = _sideEffect.receiveAsFlow()


    open fun onEvent(event: EVENT) = Unit


    protected fun updateState(reducer: STATE.() -> STATE) = _state.update { it.reducer() }

    protected fun emitSideEffect(sideEffect: SIDE_EFFECT) {
        viewModelScope.launch { _sideEffect.send(element = sideEffect) }
    }

    protected suspend fun sendSideEffect(sideEffect: SIDE_EFFECT) =
        _sideEffect.send(element = sideEffect)

    protected fun <T : Any> handleResponse(
        apiCall: () -> Flow<Resource<T>>,
        onSuccess: (T) -> Unit,
        onError: ((AppError) -> Unit)? = null,
        onLoading: (Resource.Loader) -> Unit
    ) {
        viewModelScope.launch {
            apiCall().collect { resource ->
                getResourceType(
                    resource = resource,
                    onSuccess = { onSuccess(it) },
                    onError = { onError?.invoke(it.value) },
                    onLoading = { onLoading(it) }
                )
            }
        }
    }


    /** AUX */
    private fun <T : Any> getResourceType(
        resource: Resource<T>,
        onSuccess: (T) -> Unit,
        onError: ((Resource.Error) -> Unit)? = null,
        onLoading: (Resource.Loader) -> Unit
    ) = when (resource) {
        is Resource.Success -> onSuccess(resource.data)
        is Resource.Error -> onError?.invoke(resource)
        is Resource.Loader -> onLoading(resource)
    }
}
