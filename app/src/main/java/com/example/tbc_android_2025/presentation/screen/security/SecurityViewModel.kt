package com.example.tbc_android_2025.presentation.screen.security

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewModelScope
import com.example.tbc_android_2025.commons.Colors
import com.example.tbc_android_2025.commons.Strings
import com.example.tbc_android_2025.domain.validation.PasscodeValidationResult.*
import com.example.tbc_android_2025.domain.validation.ValidatePasscodeUseCase
import com.example.tbc_android_2025.presentation.commons.BaseViewModel
import com.example.tbc_android_2025.presentation.screen.security.SecurityContract.State
import com.example.tbc_android_2025.presentation.screen.security.SecurityContract.Event
import com.example.tbc_android_2025.presentation.screen.security.SecurityContract.SideEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SecurityViewModel @Inject constructor(
    @param:ApplicationContext private val context: Context,
    private val validatePasscodeUseCase: ValidatePasscodeUseCase,
) : BaseViewModel<State, Event, SideEffect>(initialState = State()) {

    override fun onEvent(event: Event) {
        when (event) {
            is Event.DigitPressed -> handleDigit(digit = event.digit)
            is Event.BackspacePressed -> handleBackspace()
            is Event.Validate -> handleValidation()
        }
    }


    /** =================================== HANDLERS ============================================ */
    private fun handleDigit(digit: String) {
        updateState { copy(input = input + digit) }
        if (state.value.input.length == 4)
            handleValidation()
    }

    private fun handleBackspace() {
        if (state.value.input.isEmpty())
            return
        updateState { copy(input = input.dropLast(n = 1)) }
    }

    private fun handleValidation() {
        val result = validatePasscodeUseCase(input = state.value.input)

        val sideEffect = when (result) {
            is Success -> SideEffect.ShowMessage(
                text = ContextCompat.getString(context, Strings.success),
                color = Colors.viridian
            )

            is Failure -> SideEffect.ShowMessage(
                text = ContextCompat.getString(context, Strings.failure),
                color = Colors.amaranth
            )
        }

        sendEffect(sideEffect = sideEffect)

        resetStateWithDelay()
    }
    /** ========================================================================================= */


    /** ===================================== AUX =============================================== */
    private fun resetStateWithDelay(delay: Long = 100L) =
        viewModelScope.launch { delay(timeMillis = delay); clearState() }

    private fun clearState() = updateState { copy(input = "") }
    /** ========================================================================================= */
}
