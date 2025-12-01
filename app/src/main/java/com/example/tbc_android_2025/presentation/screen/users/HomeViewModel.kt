package com.example.tbc_android_2025.presentation.screen.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbc_android_2025.domain.commons.Resource.*
import com.example.tbc_android_2025.domain.use_cases.GetUsersUseCase
import com.example.tbc_android_2025.presentation.screen.users.HomeEvent.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getUsersUseCase: GetUsersUseCase) : ViewModel() {

    private val _homeState = MutableStateFlow(value = HomeState())
    val homeState: StateFlow<HomeState> = _homeState


    fun onEvent(event: HomeEvent) = with(receiver = event) {
        when (this) {
            is GetUsers -> getUsers(page = page)
        }
    }


    /** ===================================== AUX =============================================== */
    private fun getUsers(page: Int) = viewModelScope.launch {
        getUsersUseCase(page = page).collect { result ->
            when (result) {
                is Success<*> -> TODO()
                is Error -> TODO()
                is Loader -> TODO()
            }
        }
    }
    /** ========================================================================================= */
}
