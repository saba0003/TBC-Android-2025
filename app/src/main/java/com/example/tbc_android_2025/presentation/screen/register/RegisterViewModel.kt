package com.example.tbc_android_2025.presentation.screen.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbc_android_2025.commons.Strings
import com.example.tbc_android_2025.domain.commons.RegistrationValidationResult
import com.example.tbc_android_2025.domain.commons.RegistrationValidator
import com.example.tbc_android_2025.domain.commons.Resource.*
import com.example.tbc_android_2025.domain.commons.StringProvider
import com.example.tbc_android_2025.domain.use_cases.RegisterUserUseCase
import com.example.tbc_android_2025.presentation.mappers.toDomain
import com.example.tbc_android_2025.presentation.screen.register.RegisterEvent.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
    private val registrationValidator: RegistrationValidator,
    private val stringProvider: StringProvider
) : ViewModel() {

    private val _registerState = MutableStateFlow(value = RegisterState())
    val registerState: StateFlow<RegisterState> = _registerState


    fun onEvent(event: RegisterEvent) = with(receiver = event) {
        when (this) {
            is RegisterUser -> registerUser(request = request)
        }
    }


    /** ===================================== AUX =============================================== */
    private fun registerUser(request: RegisterRequest) = with(receiver = request) {
        validateFields(email = email, password = password, repeatPassword = repeatedPassword)
        viewModelScope.launch {
            registerUserUseCase(request = toDomain()).collect {
                when (it) {
                    is Success<*> -> TODO()
                    is Error -> TODO()
                    is Loader -> TODO()
                }
            }
        }
    }

    private fun validateFields(email: String, password: String, repeatPassword: String) {
        when (registrationValidator(email = email, password = password, repeatPassword = repeatPassword)) {
            is RegistrationValidationResult.Error.EmptyFields -> {
                val message = stringProvider.getString(resId = Strings.error_empty_fields)
                _registerState.update { it.copy(error = message) }
            }
            is RegistrationValidationResult.Error.InvalidEmail -> {
                val message = stringProvider.getString(resId = Strings.error_invalid_email)
                _registerState.update { it.copy(error = message) }
            }
            is RegistrationValidationResult.Error.PasswordsMismatch -> {
                val message = stringProvider.getString(resId = Strings.error_password_mismatch)
                _registerState.update { it.copy(error = message) }
            }
            is RegistrationValidationResult.Success -> TODO()
        }
    }
    /** ========================================================================================= */
}
