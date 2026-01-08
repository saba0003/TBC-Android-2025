package com.example.tbc_android_2025.presentation.screen.home

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.tbc_android_2025.domain.use_case.remote.GetUsersPageFromRemoteUseCase
import com.example.tbc_android_2025.presentation.common.BaseViewModel
import com.example.tbc_android_2025.presentation.mapper.toPresentation
import com.example.tbc_android_2025.presentation.screen.home.HomeContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(getUsersPageFromRemoteUseCase: GetUsersPageFromRemoteUseCase) :
    BaseViewModel<State, Event, SideEffect>(initialState = State.loading()) {

    val usersFlow = getUsersPageFromRemoteUseCase().map { it.toPresentation() }.cachedIn(scope = viewModelScope)

    init {
        updateState { copy(isLoading = isLoading.not()) }
    }

    override fun onEvent(event: Event) = when (event) {
        Event.OnProfileClick -> emitSideEffect(sideEffect = SideEffect.NavigateToProfile)
    }
}
