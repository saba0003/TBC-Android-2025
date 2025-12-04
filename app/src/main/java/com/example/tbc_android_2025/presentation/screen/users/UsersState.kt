package com.example.tbc_android_2025.presentation.screen.users

sealed class UsersState {
    data class Success(val data: Users) : UsersState()
    data class Error(val errorMessage: String, val throwable: Throwable? = null) : UsersState()
    data class Loader(val isLoading: Boolean) : UsersState()
}
