package com.example.tbc_android_2025.presentation.screen.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbc_android_2025.domain.commons.Resource.*
import com.example.tbc_android_2025.domain.use_cases.GetUsersUseCase
import com.example.tbc_android_2025.presentation.mappers.toPresentation
import com.example.tbc_android_2025.presentation.screen.users.HomeEvent.*
import com.example.tbc_android_2025.domain.models.responses.UsersPage as UsersPageDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getUsersUseCase: GetUsersUseCase) : ViewModel() {

    private val _homeState = MutableStateFlow(value = HomeState())
    val homeState: StateFlow<HomeState> = _homeState


    init { onEvent(event = GetUsers(page = 1)) }


    fun onEvent(event: HomeEvent) = with(receiver = event) {
        when (this) {
            is GetUsers -> getUsers(page = page)
        }
    }


    /** ===================================== AUX =============================================== */
    private fun getUsers(page: Int) = viewModelScope.launch {
        getUsersUseCase(page = page).collect { result ->
            when (result) {
                is Success<*> -> {
                    val domainModel = result.data as UsersPageDomain
                    val uiModel = domainModel.toPresentation()
                    _homeState.update { it.copy(usersPage = uiModel) }
                }
                is Error -> _homeState.update { it.copy(error = result.errorMessage) }
                is Loader -> _homeState.update { it.copy(isLoading = result.isLoading) }
            }
        }
    }
    /** ========================================================================================= */
}
