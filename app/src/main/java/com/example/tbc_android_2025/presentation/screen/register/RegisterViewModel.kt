package com.example.tbc_android_2025.presentation.screen.register

import androidx.lifecycle.viewModelScope
import com.example.tbc_android_2025.commons.Strings
import com.example.tbc_android_2025.domain.validations.register.RegistrationValidationResult
import com.example.tbc_android_2025.domain.validations.register.RegistrationValidator
import com.example.tbc_android_2025.domain.commons.Resource.*
import com.example.tbc_android_2025.domain.commons.ResourceProvider
import com.example.tbc_android_2025.domain.use_cases.RegisterUserUseCase
import com.example.tbc_android_2025.presentation.commons.BaseViewModel
import com.example.tbc_android_2025.presentation.mappers.toDomain
import com.example.tbc_android_2025.presentation.screen.register.RegisterEvent.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
    private val registrationValidator: RegistrationValidator,
    private val resourceProvider: ResourceProvider
) : BaseViewModel<RegisterState, RegisterEvent>(initialState = RegisterState()) {

    override fun onEvent(event: RegisterEvent) = with(receiver = event) {
        when (this) {
            is RegisterUser -> registerUser(request = request)
        }
    }


    /** ===================================== AUX =============================================== */
    private fun registerUser(request: RegisterRequest): Unit = with(receiver = request) {
        if (!validateFields(email = email, password = password, repeatPassword = repeatedPassword))
            return

        viewModelScope.launch {
            registerUserUseCase(request = toDomain()).collect {
                when (it) {
                    is Success -> updateState { copy(isSuccess = true) }
                    is Error -> updateState { copy(error = it.errorMessage) }
                    is Loader -> updateState { copy(isLoading = it.isLoading) }
                }
            }
        }
    }

    private fun validateFields(email: String, password: String, repeatPassword: String): Boolean {
        clearError()

        val errorMessage: String? = when (registrationValidator(
            email = email,
            password = password,
            repeatPassword = repeatPassword
        )) {
            is RegistrationValidationResult.Error.EmptyFields -> resourceProvider.getString(resId = Strings.error_empty_fields)
            is RegistrationValidationResult.Error.InvalidEmail -> resourceProvider.getString(resId = Strings.error_invalid_email)
            is RegistrationValidationResult.Error.PasswordsMismatch -> resourceProvider.getString(
                resId = Strings.error_password_mismatch
            )
            is RegistrationValidationResult.Success -> null
        }

        errorMessage?.let { updateState { copy(error = it) } }

        return errorMessage == null
    }

    private fun clearError() = updateState { copy(error = null) }
    /** ========================================================================================= */
}
