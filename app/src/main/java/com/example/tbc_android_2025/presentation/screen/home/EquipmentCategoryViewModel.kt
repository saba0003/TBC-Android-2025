package com.example.tbc_android_2025.presentation.screen.home

import com.example.tbc_android_2025.domain.use_case.remote.GetEquipmentCategoryModelsFromRemoteUseCase
import com.example.tbc_android_2025.presentation.common.BaseViewModel
import com.example.tbc_android_2025.presentation.mapper.toPresentation
import com.example.tbc_android_2025.presentation.screen.home.EquipmentCategoryContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EquipmentCategoryViewModel @Inject constructor(
    private val getEquipmentCategoryModelsFromRemoteUseCase: GetEquipmentCategoryModelsFromRemoteUseCase
) : BaseViewModel<State, Event, SideEffect>(initialState = State(isLoading = true)) {


    init {
        onEvent(event = Event.OnLoadEquipmentCategoryModelsFromRemote)
    }


    override fun onEvent(event: Event) = with(receiver = event) {
        when (this) {
            Event.OnLoadEquipmentCategoryModelsFromRemote -> onLoadEquipmentCategoryModelsFromRemote()
            is Event.OnSearch -> onSearch(query = query)
            Event.OnLoadTemplateModelsFromLocal -> Unit
        }
    }


    /** ======================================= HANDLERS ======================================== */
    private fun onLoadEquipmentCategoryModelsFromRemote() = handleResponse(
        apiCall = { getEquipmentCategoryModelsFromRemoteUseCase() },
        onSuccess = {
            updateState { copy(data = it.toPresentation(), isLoading = isLoading.not()) }
        },
        onError = {
            updateState { copy(isLoading = isLoading.not()) }
            emitSideEffect(sideEffect = SideEffect.ShowError(error = it))
        },
        onLoading = { updateState { copy(isLoading = it.isLoading) } }
    )

    private fun onSearch(query: String?) {
        if (query.isNullOrBlank()) {
            updateState { copy(filteredData = emptyList()) }
            return
        }

        val allMatches = mutableListOf<EquipmentCategoryModel>()

        fun searchDeep(categories: List<EquipmentCategoryModel>) {
            for (category in categories) {
                if (category.name.contains(other = query, ignoreCase = true))
                    allMatches.add(element = category)
                if (category.children.isNotEmpty())
                    searchDeep(categories = category.children)
            }
        }

        searchDeep(categories = state.value.data)
        updateState { copy(filteredData = allMatches) }
    }
    /** ========================================================================================= */
}
