package com.example.tbc_android_2025.presentation.screen.splash

import androidx.lifecycle.viewModelScope
import com.example.tbc_android_2025.domain.data_store.DataStoreKeys.TOKEN_KEY
import com.example.tbc_android_2025.domain.network.ConnectivityObserver
import com.example.tbc_android_2025.domain.use_case.remote.GetTemplateModelsFromRemoteUseCase
import com.example.tbc_android_2025.domain.use_case.data_store.GetPreferenceUseCase
import com.example.tbc_android_2025.domain.use_case.local.GetTemplateModelsFromLocalUseCase
import com.example.tbc_android_2025.presentation.common.view_model.BaseViewModel
import com.example.tbc_android_2025.presentation.mapper.toPresentation
import com.example.tbc_android_2025.presentation.screen.splash.SplashContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getPreferenceUseCase: GetPreferenceUseCase,
    private val getTemplateModelsFromRemoteUseCase: GetTemplateModelsFromRemoteUseCase,
    private val getTemplateModelsFromLocalUseCase: GetTemplateModelsFromLocalUseCase,
    private val connectivityObserver: ConnectivityObserver
) : BaseViewModel<State, Event, SideEffect>(initialState = State.loading()) {


    init {
        onEvent(event = Event.OnLoadTemplateModelsFromRemoteAndProceed)
    }


    override fun onEvent(event: Event) {
        when (event) {
            Event.OnLoadTemplateModelsFromRemoteAndProceed -> {
                viewModelScope.launch {
                    val isConnected = connectivityObserver.isConnected.first()
                    if (isConnected) {
                        val token =
                            getPreferenceUseCase(
                                key = TOKEN_KEY,
                                defaultValue = EMPTY_STRING
                            ).first()
                        if (token.isNotBlank())
                            onLoadTemplateModelsFromRemoteAndProceed()
                    } else {
                        onLoadTemplateEntitiesFromLocalAndProceed()
                    }
                }
            }

            Event.OnLoadTemplateModelsFromLocalAndProceed -> onLoadTemplateEntitiesFromLocalAndProceed()
        }
    }


    /** ======================================= HANDLERS ======================================== */
    private fun onLoadTemplateModelsFromRemoteAndProceed() = handleResponse(
        apiCall = { getTemplateModelsFromRemoteUseCase() },
        onSuccess = { updateState { copy(isLoading = isLoading.not()) } },
        onError = {
            updateState { copy(isLoading = isLoading.not()) }
            emitSideEffect(sideEffect = SideEffect.ShowError(error = it))
            onLoadTemplateEntitiesFromLocalAndProceed()
        },
        onLoading = { updateState { copy(isLoading = it.isLoading) } }
    )

    private fun onLoadTemplateEntitiesFromLocalAndProceed() {
        viewModelScope.launch(context = Dispatchers.IO) {
            getTemplateModelsFromLocalUseCase().collect {
                updateState { copy(data = it.toPresentation()) }
            }
        }
    }
    /** ========================================================================================= */

    private companion object {
        const val EMPTY_STRING = ""
    }
}
