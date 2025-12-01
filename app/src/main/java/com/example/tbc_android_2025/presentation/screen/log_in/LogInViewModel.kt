package com.example.tbc_android_2025.presentation.screen.log_in

import androidx.lifecycle.viewModelScope
import com.example.tbc_android_2025.commons.Strings
import com.example.tbc_android_2025.domain.validations.log_in.LogInValidationResult
import com.example.tbc_android_2025.domain.validations.log_in.LogInValidator
import com.example.tbc_android_2025.domain.commons.Resource.*
import com.example.tbc_android_2025.domain.commons.ResourceProvider
import com.example.tbc_android_2025.domain.use_cases.LogInUserUseCase
import com.example.tbc_android_2025.presentation.commons.BaseViewModel
import com.example.tbc_android_2025.presentation.mappers.toDomain
import com.example.tbc_android_2025.presentation.screen.log_in.LogInEvent.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val logInUserUseCase: LogInUserUseCase,
    private val logInValidator: LogInValidator,
    private val resourceProvider: ResourceProvider
) : BaseViewModel<LogInState, LogInEvent>(initialState = LogInState()) {

    override fun onEvent(event: LogInEvent) = with(receiver = event) {
        when (this) {
            is LogInUser -> logInUser(request = request)
        }
    }


    /** ===================================== AUX =============================================== */
    private fun logInUser(request: LogInRequest): Unit = with(receiver = request) {
        if (!validateFields(email = email, password = password))
            return

        viewModelScope.launch {
            logInUserUseCase(request = toDomain()).collect {
                when (it) {
                    is Success<*> -> updateState { copy(isSuccess = true) }
                    is Error -> updateState { copy(error = it.errorMessage) }
                    is Loader -> updateState { copy(isLoading = it.isLoading) }
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

        errorMessage?.let { updateState { copy(error = it) } }

        return errorMessage == null
    }

    private fun clearError() = updateState { copy(error = null) }
    /** ========================================================================================= */
}
