package com.example.tbc_android_2025.presentation.screen.template

import com.example.tbc_android_2025.domain.use_case.GetTemplateModelsUseCase
import com.example.tbc_android_2025.presentation.common.BaseViewModel
import com.example.tbc_android_2025.presentation.mapper.toPresentation
import com.example.tbc_android_2025.presentation.screen.template.TemplateContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TemplateViewModel @Inject constructor(private val getTemplateModels: GetTemplateModelsUseCase) :
    BaseViewModel<State, Event, SideEffect>(initialState = State(isLoading = true)) {


    init {
        onEvent(event = Event.GetTemplateModels)
    }


    override fun onEvent(event: Event) = with(receiver = event) {
        when (this) {
            Event.GetTemplateModels -> onGetTemplateModels()
        }
    }


    /** ======================================= HANDLERS ======================================== */
    private fun onGetTemplateModels() = handleResponse(
        apiCall = { getTemplateModels() },
        onSuccess = { updateState { copy(data = it.toPresentation()) } },
        onError = {
            updateState { copy(error = it) }
            emitSideEffect(sideEffect = SideEffect.ShowError(error = it))
        },
        onLoading = { updateState { copy(isLoading = it.isLoading) } }
    )
    /** ========================================================================================= */
}
