package com.example.tbc_android_2025.presentation.screens.template

sealed interface TemplateContract {

    data class State(
        val data: TemplateModels = TemplateModels(data = emptyList()),
        val error: String? = null,
        val isLoading: Boolean = false
    )

    sealed interface Event {
        data object GetTemplateModels : Event
    }

}
