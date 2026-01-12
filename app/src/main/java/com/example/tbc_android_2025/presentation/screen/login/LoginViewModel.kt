package com.example.tbc_android_2025.presentation.screen.login

import com.example.tbc_android_2025.domain.model.request.LoginRequestModel
import com.example.tbc_android_2025.domain.use_case.remote.LoginUseCase
import com.example.tbc_android_2025.presentation.common.view_model.BaseViewModel
import com.example.tbc_android_2025.presentation.screen.login.LoginContract.Event
import com.example.tbc_android_2025.presentation.screen.login.LoginContract.SideEffect
import com.example.tbc_android_2025.presentation.screen.login.LoginContract.State
import com.example.tbc_android_2025.presentation.util.NotificationHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val notificationHelper: NotificationHelper
) : BaseViewModel<State, Event, SideEffect>(initialState = State(isLoading = true)) {

    init {
        updateState { copy(isLoading = isLoading.not()) }
    }

    override fun onEvent(event: Event) = when (event) {
        is Event.OnEmailChange -> updateState { copy(email = event.text) }
        is Event.OnPasswordChange -> updateState { copy(password = event.text) }
        Event.OnEyeClick -> updateState { copy(isPasswordVisible = isPasswordVisible.not()) }
        Event.OnRememberMeClick -> updateState { copy(rememberMe = rememberMe.not()) }
        Event.OnLoginClick -> {
            val request =
                LoginRequestModel(email = state.value.email, password = state.value.password)
            handleResponse(
                apiCall = { loginUseCase(request = request) },
                onSuccess = {
                    notificationHelper.showLoginSuccess()
                    emitSideEffect(sideEffect = SideEffect.NavigateToHome)
                },
                onError = { emitSideEffect(sideEffect = SideEffect.ShowError(error = it)) },
                onLoading = { updateState { copy(isLoading = it.isLoading) } }
            )
        }
    }
}
