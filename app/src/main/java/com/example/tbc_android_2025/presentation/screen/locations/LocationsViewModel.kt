package com.example.tbc_android_2025.presentation.screen.locations

import androidx.lifecycle.viewModelScope
import com.example.tbc_android_2025.domain.use_case.local.GetThemeUseCase
import com.example.tbc_android_2025.domain.use_case.local.SetThemeUseCase
import com.example.tbc_android_2025.domain.use_case.remote.GetLocationsUseCase
import com.example.tbc_android_2025.presentation.common.BaseViewModel
import com.example.tbc_android_2025.presentation.mapper.toPresentation
import com.example.tbc_android_2025.presentation.screen.locations.LocationsContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val getLocationsUseCase: GetLocationsUseCase,
    private val getThemeUseCase: GetThemeUseCase,
    private val setThemeUseCase: SetThemeUseCase
) : BaseViewModel<State, Event, SideEffect>(initialState = State.loading()) {

    init {
        onEvent(event = Event.OnFetchLocations)
    }

    override fun onEvent(event: Event) {
        when (event) {
            Event.OnFetchLocations -> handleResponse(
                apiCall = { getLocationsUseCase() },
                onSuccess = { updateState { copy(locations = it.toPresentation()) } },
                onError = { sendSideEffect(sideEffect = SideEffect.ShowError(error = it)) },
                onLoading = { updateState { copy(isLoading = it.isLoading) } }
            )

            is Event.OnThemeToggle -> viewModelScope.launch { setThemeUseCase(isDark = event.isDark) }
        }
    }

    private fun observeTheme() {
        viewModelScope.launch {
            getThemeUseCase().collect { isDark ->
                updateState { copy(isDarkMode = isDark) }
            }
        }
    }
}
