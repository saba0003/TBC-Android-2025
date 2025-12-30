package com.example.tbc_android_2025.presentation.screen.home

import androidx.lifecycle.viewModelScope
import com.example.tbc_android_2025.domain.use_case.remote.FilterEquipmentCategoryModelsByNameUseCase
import com.example.tbc_android_2025.domain.use_case.remote.GetEquipmentCategoryModelsFromRemoteUseCase
import com.example.tbc_android_2025.presentation.common.BaseViewModel
import com.example.tbc_android_2025.presentation.mapper.toDomain
import com.example.tbc_android_2025.presentation.mapper.toPresentation
import com.example.tbc_android_2025.presentation.model.EquipmentCategoryModel
import com.example.tbc_android_2025.presentation.screen.home.HomeContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getEquipmentCategoryModelsFromRemoteUseCase: GetEquipmentCategoryModelsFromRemoteUseCase,
    private val filterEquipmentCategoryModelsByNameUseCase: FilterEquipmentCategoryModelsByNameUseCase
) : BaseViewModel<State, Event, SideEffect>(initialState = State(isLoading = true)) {


    init {
        onEvent(event = Event.OnLoadEquipmentCategoryModelsFromRemote)
    }


    override fun onEvent(event: Event) = with(receiver = event) {
        when (this) {
            Event.OnLoadEquipmentCategoryModelsFromRemote -> onLoadEquipmentCategoryModelsFromRemote()
            is Event.OnSearch -> onSearch(query = query)
            is Event.OnEquipmentCategoryClick -> onEquipmentCategoryClick(equipmentCategory = equipmentCategory)
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

        viewModelScope.launch(context = Dispatchers.Default) {
            val filteredResults = filterEquipmentCategoryModelsByNameUseCase(
                query = query, categories = state.value.data.toDomain()
            )

            withContext(context = Dispatchers.Main) {
                updateState { copy(filteredData = filteredResults.toPresentation()) }
            }
        }
    }

    @OptIn(ExperimentalUuidApi::class)
    private fun onEquipmentCategoryClick(equipmentCategory: EquipmentCategoryModel) {
        val strippedEquipmentCategory = equipmentCategory.copy(children = emptyList())
        emitSideEffect(sideEffect = SideEffect.NavigateToEquipmentCategoryDetails(equipmentCategory = strippedEquipmentCategory))
    }
    /** ========================================================================================= */
}
