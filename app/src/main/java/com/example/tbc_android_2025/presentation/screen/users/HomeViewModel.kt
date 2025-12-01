package com.example.tbc_android_2025.presentation.screen.users

import androidx.lifecycle.viewModelScope
import com.example.tbc_android_2025.domain.commons.Resource.*
import com.example.tbc_android_2025.domain.use_cases.GetUsersUseCase
import com.example.tbc_android_2025.presentation.commons.BaseViewModel
import com.example.tbc_android_2025.presentation.mappers.toPresentation
import com.example.tbc_android_2025.presentation.screen.users.HomeEvent.*
import com.example.tbc_android_2025.domain.models.responses.UsersPage as UsersPageDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getUsersUseCase: GetUsersUseCase) :
    BaseViewModel<HomeState, HomeEvent>(initialState = HomeState()) {

    override fun onEvent(event: HomeEvent): Unit = with(receiver = event) {
        when (this) {
            is GetUsers -> getUsers(page = page)
        }
    }


    /** ===================================== AUX =============================================== */
    private fun getUsers(page: Int) = viewModelScope.launch {
        getUsersUseCase(page = page).collect {
            when (it) {
                is Success<*> -> {
                    val domainModel = it.data as UsersPageDomain
                    val uiModel = domainModel.toPresentation()
                    updateState { copy(usersPage = uiModel) }
                }
                is Error -> updateState { copy(error = it.errorMessage) }
                is Loader -> updateState { copy(isLoading = it.isLoading) }
            }
        }
    }
    /** ========================================================================================= */
}
