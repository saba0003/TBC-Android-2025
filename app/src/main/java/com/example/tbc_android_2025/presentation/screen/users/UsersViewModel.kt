package com.example.tbc_android_2025.presentation.screen.users

import androidx.lifecycle.viewModelScope
import com.example.tbc_android_2025.domain.commons.Resource
import com.example.tbc_android_2025.domain.use_cases.GetUsersUseCase
import com.example.tbc_android_2025.presentation.commons.BaseViewModel
import com.example.tbc_android_2025.presentation.mappers.toPresentation
import com.example.tbc_android_2025.presentation.screen.users.UsersEvent.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val getUsersUseCase: GetUsersUseCase) :
    BaseViewModel<UsersState, UsersEvent, Unit>(initialState = UsersState.Loader(isLoading = true)) {


    init { handleGetUsers() }


    override fun onEvent(event: UsersEvent): Unit = with(receiver = event) {
        when (this) {
            is GetUsers -> handleGetUsers()
        }
    }


    /** ======================================= HANDLERS ======================================== */
    private fun handleGetUsers() {
        viewModelScope.launch {
            getUsersUseCase().collect {
                when (it) {
                    is Resource.Success -> updateState { UsersState.Success(data = it.data.toPresentation()) }
                    is Resource.Error -> updateState {
                        UsersState.Error(errorMessage = it.errorMessage, throwable = it.throwable)
                    }
                    is Resource.Loader -> updateState { UsersState.Loader(isLoading = it.isLoading) }
                }
            }
        }
    }
    /** ========================================================================================= */
}