package com.example.tbc_android_2025.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbc_android_2025.domain.commons.Resource
import com.example.tbc_android_2025.domain.use_cases.GetChatsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatsViewModel @Inject constructor(private val getChatsUseCase: GetChatsUseCase) : ViewModel() {

    private val _state = MutableStateFlow(value = ChatsState())
    private val _sideEffect = MutableSharedFlow<ChatsSideEffect>()
    val state = _state.asSharedFlow()
    val sideEffect = _sideEffect.asSharedFlow()


    fun onEvent(event: ChatsEvent) = when (event) {
        is ChatsEvent.GetUsers -> loadChats()
    }


    private fun loadChats() =
        viewModelScope.launch {
            getChatsUseCase().collect { resource ->
                when (resource) {

                    is Resource.Loader -> _state.update { it.copy(isLoading = resource.isLoading) }

                    is Resource.Success -> _state.update {
                        it.copy(
                            isLoading = false,
                            chats = resource.data,
                            error = null
                        )
                    }

                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = resource.errorMessage
                            )
                        }

                        _sideEffect.emit(
                            value = ChatsSideEffect.ShowError(errorMessage = resource.errorMessage)
                        )
                    }
                }
            }
        }
}
