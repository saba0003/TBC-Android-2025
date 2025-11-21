package com.example.tbc_android_2025.presentation.screen.log_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbc_android_2025.data.auth.commons.Resource
import com.example.tbc_android_2025.data.auth.log_in.repository.LogInRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(private val repository: LogInRepository) : ViewModel() {

    private val _state = MutableStateFlow(value = LogInState())
    private val _sideEffect = MutableSharedFlow<LogInSideEffect>()
    val state = _state.asSharedFlow()
    val sideEffect = _sideEffect.asSharedFlow()


    fun onEvent(event: LogInEvent) {
        when (event) {
            is LogInEvent.LogIn -> logIn(email = event.email, password = event.password)
        }
    }

    private fun logIn(email: String, password: String) {
        viewModelScope.launch {
            repository.logIn(email = email, password = password)
                .collect { resource ->
                    when (resource) {
                        is Resource.Loader -> _state.update { it.copy(loader = resource.isLoading) }
                        is Resource.Success -> _state.update { it.copy(loader = resource.data.token) }
                        is Resource.Error -> _sideEffect.emit(
                            value = LogInSideEffect.ShowError(
                                errorMessage = resource.errorMessage
                            )
                        )
                    }
                }
        }
    }
}
