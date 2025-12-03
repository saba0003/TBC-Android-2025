package com.example.tbc_android_2025.presentation.screen.security

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.tbc_android_2025.BuildConfig
import com.example.tbc_android_2025.commons.Colors
import com.example.tbc_android_2025.commons.Strings
import com.example.tbc_android_2025.presentation.commons.BaseViewModel
import com.example.tbc_android_2025.presentation.screen.security.SecurityContract.State
import com.example.tbc_android_2025.presentation.screen.security.SecurityContract.Event
import com.example.tbc_android_2025.presentation.screen.security.SecurityContract.SideEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class SecurityViewModel @Inject constructor(@param:ApplicationContext private val context: Context) :
    BaseViewModel<State, Event, SideEffect>(initialState = State()) {

    private val correctPasscode = BuildConfig.PASSCODE


    override fun onEvent(event: Event) {
        when (event) {
            is Event.DigitPressed -> handleDigit(digit = event.digit)
            is Event.BackspacePressed -> handleBackspace()
            is Event.Validate -> validate()
        }
    }


    /** ===================================== AUX =============================================== */
    private fun handleDigit(digit: String) {
        if (state.value.input.length >= 4)
            return
        updateState { copy(input = input + digit) }
        if (state.value.input.length == 4)
            validate()
    }

    private fun handleBackspace() {
        if (state.value.input.isEmpty())
            return
        updateState { copy(input = input.dropLast(n = 1)) }
    }

    private fun validate() {
        val input = state.value.input
        val isCorrect = input == correctPasscode

        sendEffect(
            sideEffect = SideEffect.ShowMessage(
                text = if (isCorrect)
                    ContextCompat.getString(context, Strings.success)
                else
                    ContextCompat.getString(context, Strings.failure),
                color = if (isCorrect)
                    Colors.viridian
                else
                    Colors.amaranth
            )
        )

        clearState()
    }

    private fun clearState() = updateState { copy(input = "") }

//    private fun resetStateWithDelay() {
//        // Delay so Snackbar can show before resetting UI
//        view?.postDelayed({ currentInput.clear(); updateDots() }, 300)
//    }
    /** ========================================================================================= */
}
