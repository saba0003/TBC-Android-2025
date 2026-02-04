package com.example.register.presentation.screen

import com.example.presentation.common.BaseViewModel
import com.example.register.domain.use_case.remote.GetFieldsUseCase
import com.example.register.presentation.mapper.toPresentation
import com.example.register.presentation.screen.RegisterContract.*
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
