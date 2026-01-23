package com.example.tbc_android_2025.presentation.screen.chats

import com.example.tbc_android_2025.domain.use_case.remote.GetChatsUseCase
import com.example.tbc_android_2025.presentation.common.BaseViewModel
import com.example.tbc_android_2025.presentation.mapper.toPresentation
import com.example.tbc_android_2025.presentation.screen.chats.ChatsContract.*
import com.example.tbc_android_2025.presentation.screen.chats.ChatsContract.SideEffect.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatsViewModel @Inject constructor(private val getChatsUseCase: GetChatsUseCase) :
    BaseViewModel<State, Event, SideEffect>(initialState = State.loading()) {

    init {
        onEvent(event = Event.OnFetchChats)
    }

    override fun onEvent(event: Event) = when (event) {
        Event.OnFetchChats -> handleResponse(
            apiCall = { getChatsUseCase() },
            onSuccess = { updateState { copy(chats = it.toPresentation()) } },
            onError = { sendSideEffect(sideEffect = ShowError(error = it)) },
            onLoading = { updateState { copy(isLoading = it.isLoading) } }
        )

        is Event.OnSearch -> updateState { copy(searchQuery = event.query) }
    }
}
