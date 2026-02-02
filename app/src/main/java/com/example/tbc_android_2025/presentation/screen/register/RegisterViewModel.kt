package com.example.tbc_android_2025.presentation.screen.register

import com.example.tbc_android_2025.domain.use_case.remote.GetFieldsUseCase
import com.example.tbc_android_2025.presentation.common.BaseViewModel
import com.example.tbc_android_2025.presentation.mapper.toPresentation
import com.example.tbc_android_2025.presentation.screen.register.RegisterContract.Event
import com.example.tbc_android_2025.presentation.screen.register.RegisterContract.SideEffect
import com.example.tbc_android_2025.presentation.screen.register.RegisterContract.State
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val getFieldsUseCase: GetFieldsUseCase) :
    BaseViewModel<State, Event, SideEffect>(initialState = State.loading()) {

    init {
        onEvent(event = Event.OnFetchFields)
    }


    override fun onEvent(event: Event) {
        when (event) {
            Event.OnFetchFields -> handleFetchLocations()
        }
    }


    /** ======================================= HANDLERS ======================================== */
    private fun handleFetchLocations() = handleResponse(
        apiCall = { getFieldsUseCase() },
        onSuccess = { updateState { copy(fields = it.toPresentation()) } },
        onError = { sendSideEffect(sideEffect = SideEffect.ShowError(error = it)) },
        onLoading = { updateState { copy(isLoading = it.isLoading) } }
    )
    /** ========================================================================================= */
}
