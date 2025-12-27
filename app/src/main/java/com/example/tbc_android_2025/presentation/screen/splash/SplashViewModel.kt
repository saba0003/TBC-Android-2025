package com.example.tbc_android_2025.presentation.screen.splash

import androidx.lifecycle.viewModelScope
import com.example.tbc_android_2025.domain.data_store.DataStoreKeys.TOKEN_KEY
import com.example.tbc_android_2025.domain.use_case.GetTemplateModelsUseCase
import com.example.tbc_android_2025.domain.use_case.data_store.GetPreferenceUseCase
import com.example.tbc_android_2025.presentation.common.BaseViewModel
import com.example.tbc_android_2025.presentation.screen.splash.SplashContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getPreferenceUseCase: GetPreferenceUseCase,
    private val getTemplateModels: GetTemplateModelsUseCase
) : BaseViewModel<State, Unit, SideEffect>(initialState = State(isLoading = true)) {


    init {
        viewModelScope.launch {
            val token = getPreferenceUseCase(key = TOKEN_KEY, defaultValue = EMPTY_STRING).first()
            if (token.isNotBlank())
                loadTemplateModelsAndProceed()
        }
    }


    /** AUX */
    private fun loadTemplateModelsAndProceed() = handleResponse(
        apiCall = { getTemplateModels() },
        onSuccess = { updateState { copy(isLoading = isLoading.not()) } },
        onError = {
            updateState { copy(isLoading = isLoading.not()) }
            emitSideEffect(sideEffect = SideEffect.ShowError(error = it))
        },
        onLoading = { updateState { copy(isLoading = it.isLoading) } }
    )


    private companion object {
        const val EMPTY_STRING = ""
    }
}
