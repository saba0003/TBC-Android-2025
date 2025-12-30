package com.example.tbc_android_2025.presentation.screen.home

import com.example.tbc_android_2025.domain.error.AppError

sealed interface EquipmentCategoryContract {
    data class State(
        val data: List<EquipmentCategoryModel> = emptyList(),
        val filteredData: List<EquipmentCategoryModel> = emptyList(),
        val error: AppError? = null,
        val isLoading: Boolean = false
    )

    sealed interface Event {
        data object OnLoadEquipmentCategoryModelsFromRemote : Event
        data class OnSearch(val query: String?) : Event
        data object OnLoadTemplateModelsFromLocal : Event
    }

    sealed interface SideEffect {
        data class ShowError(val error: AppError) : SideEffect
    }
}
