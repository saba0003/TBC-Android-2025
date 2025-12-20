package com.example.tbc_android_2025.presentation.screen.template

import androidx.lifecycle.viewModelScope
import com.example.tbc_android_2025.domain.common.Resource
import com.example.tbc_android_2025.domain.use_case.GetTemplateModelsUseCase
import com.example.tbc_android_2025.presentation.common.BaseViewModel
import com.example.tbc_android_2025.presentation.mapper.toPresentation
import com.example.tbc_android_2025.presentation.screen.template.TemplateContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TemplateViewModel @Inject constructor(private val getTemplateModels: GetTemplateModelsUseCase) :
    BaseViewModel<State, Event, SideEffect>(initialState = State(isLoading = true)) {


    init { onEvent(event = Event.GetTemplateModels) }


    override fun onEvent(event: Event) = with(receiver = event) {
        when (this) {
            Event.GetTemplateModels -> onGetTemplateModels()
        }
    }


    /** ======================================= HANDLERS ======================================== */
    private fun onGetTemplateModels() {
        viewModelScope.launch {
            getTemplateModels().collect {
                when (it) {
                    is Resource.Success -> updateState { copy(data = it.data.toPresentation()) }
                    is Resource.Error -> {
                        updateState { copy(errorCode = it.errorCode) }
                        emitSideEffect(sideEffect = SideEffect.ShowError(errorCode = it.errorCode))
                    }
                    is Resource.Loader -> updateState { copy(isLoading = it.isLoading) }
                }
            }
        }
    }
    /** ========================================================================================= */
}
