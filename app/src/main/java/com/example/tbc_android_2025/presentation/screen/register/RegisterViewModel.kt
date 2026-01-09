package com.example.tbc_android_2025.presentation.screen.register

import com.example.tbc_android_2025.domain.model.request.RegisterRequestModel
import com.example.tbc_android_2025.domain.use_case.remote.RegisterUseCase
import com.example.tbc_android_2025.presentation.common.BaseViewModel
import com.example.tbc_android_2025.presentation.screen.register.RegisterContract.*
import com.example.tbc_android_2025.presentation.util.NotificationHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val notificationHelper: NotificationHelper
) : BaseViewModel<State, Event, SideEffect>(initialState = State.loading()) {

    init {
        updateState { copy(isLoading = isLoading.not()) }
    }

    override fun onEvent(event: Event) = when (event) {
        is Event.OnEmailChange -> updateState { copy(email = event.text) }
        is Event.OnUsernameChange -> updateState { copy(username = event.text) }
        is Event.OnPasswordChange -> updateState { copy(password = event.text) }
        is Event.OnConfirmPasswordChange -> updateState { copy(confirmPassword = event.text) }
        Event.OnEyeClick -> updateState { copy(isPasswordVisible = isPasswordVisible.not()) }
        Event.OnRegisterClick -> {
            val request = RegisterRequestModel(
                email = state.value.email,
                password = state.value.password,
                confirmPassword = state.value.confirmPassword
            )
            handleResponse(
                apiCall = { registerUseCase(request = request) },
                onSuccess = {
                    notificationHelper.showRegisterSuccess()
                    emitSideEffect(sideEffect = SideEffect.NavigateToHome)
                },
                onError = { emitSideEffect(sideEffect = SideEffect.ShowError(error = it)) },
                onLoading = { updateState { copy(isLoading = it.isLoading) } }
            )
        }
    }
}
