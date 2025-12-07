package com.example.tbc_android_2025.presentation.screen.template

import androidx.lifecycle.viewModelScope
import com.example.tbc_android_2025.domain.commons.Resource
import com.example.tbc_android_2025.domain.use_cases.GetTemplateModelsUseCase
import com.example.tbc_android_2025.presentation.commons.BaseViewModel
import com.example.tbc_android_2025.presentation.mappers.toPresentation
import com.example.tbc_android_2025.presentation.screen.template.TemplateContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TemplateViewModel @Inject constructor(private val getTemplateModels: GetTemplateModelsUseCase) :
    BaseViewModel<State, Event, Unit>(initialState = State(isLoading = true)) {


    init { onEvent(event = Event.GetTemplateModels) }


    override fun onEvent(event: Event): Unit = with(receiver = event) {
        when (this) {
            Event.GetTemplateModels -> onGetTemplateModels()
        }
    }


    /** ======================================= HANDLERS ======================================== */
    private fun onGetTemplateModels() = viewModelScope.launch {
        getTemplateModels().collect {
            when (it) {
                is Resource.Success -> updateState { State(data = it.data.toPresentation()) }
                is Resource.Error -> updateState { State(error = it.errorMessage) }
                is Resource.Loader -> updateState { State(isLoading = it.isLoading) }
            }
        }
    }
    /** ========================================================================================= */
}
