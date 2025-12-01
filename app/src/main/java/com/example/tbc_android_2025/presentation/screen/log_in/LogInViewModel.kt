package com.example.tbc_android_2025.presentation.screen.log_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbc_android_2025.commons.Strings
import com.example.tbc_android_2025.domain.validations.log_in.LogInValidationResult
import com.example.tbc_android_2025.domain.validations.log_in.LogInValidator
import com.example.tbc_android_2025.domain.commons.Resource.*
import com.example.tbc_android_2025.domain.commons.ResourceProvider
import com.example.tbc_android_2025.domain.use_cases.LogInUserUseCase
import com.example.tbc_android_2025.presentation.mappers.toDomain
import com.example.tbc_android_2025.presentation.screen.log_in.LogInEvent.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val logInUserUseCase: LogInUserUseCase,
    private val logInValidator: LogInValidator,
    private val resourceProvider: ResourceProvider
) : ViewModel() {

    private val _logInState = MutableStateFlow(value = LogInState())
    val logInState: StateFlow<LogInState> = _logInState


    fun onEvent(event: LogInEvent) = with(receiver = event) {
        when (this) {
            is LogInUser -> logInUser(request = request)
        }
    }


    /** ===================================== AUX =============================================== */
    private fun logInUser(request: LogInRequest): Unit = with(receiver = request) {
        if (!validateFields(email = email, password = password))
            return

        viewModelScope.launch {
            logInUserUseCase(request = toDomain()).collect { result ->
                when (result) {
                    is Success<*> -> _logInState.update { it.copy(isSuccess = true) }
                    is Error -> _logInState.update { it.copy(error = result.errorMessage) }
                    is Loader -> _logInState.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    private fun validateFields(email: String, password: String): Boolean {
        clearError()

        val errorMessage: String? = when (logInValidator(email = email, password = password)) {
            is LogInValidationResult.Error.EmptyFields -> resourceProvider.getString(resId = Strings.error_empty_fields)
            is LogInValidationResult.Error.InvalidEmail -> resourceProvider.getString(resId = Strings.error_invalid_email)
            is LogInValidationResult.Success -> null
        }

        errorMessage?.let { message -> _logInState.update { it.copy(error = message) } }

        return errorMessage == null
    }

    private fun clearError() = _logInState.update { it.copy(error = null) }
    /** ========================================================================================= */
}