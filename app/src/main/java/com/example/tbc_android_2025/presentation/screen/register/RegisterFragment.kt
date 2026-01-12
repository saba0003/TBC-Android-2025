package com.example.tbc_android_2025.presentation.screen.register

import android.annotation.SuppressLint
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.MotionEvent
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tbc_android_2025.databinding.FragmentRegisterBinding as Binding
import com.example.tbc_android_2025.presentation.common.fragment.BaseMviFragment
import com.example.tbc_android_2025.presentation.common.Colors
import com.example.tbc_android_2025.presentation.extension.asString
import com.example.tbc_android_2025.presentation.extension.popMessage
import com.example.tbc_android_2025.presentation.screen.register.RegisterContract.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment :
    BaseMviFragment<Binding, State, SideEffect, RegisterViewModel>(inflater = Binding::inflate) {

    override val viewModel: RegisterViewModel by viewModels()


    override fun listeners() {
        setListenerOnEmailEditText()
        setListenerOnUsernameEditText()
        setListenerOnPasswordEditText()
        setListenerOnConfirmPasswordEditText()
        setListenerOnEyeButton()
        setListenerOnRegisterButton()
    }


    /** ======================================= LISTENERS ======================================= */
    private fun setListenerOnEmailEditText() = binding.emailEditText.doAfterTextChanged {
        viewModel.onEvent(event = Event.OnEmailChange(text = it.toString()))
    }

    private fun setListenerOnUsernameEditText() = binding.usernameEditText.doAfterTextChanged {
        viewModel.onEvent(event = Event.OnUsernameChange(text = it.toString()))
    }

    private fun setListenerOnPasswordEditText() = binding.passwordEditText.doAfterTextChanged {
        viewModel.onEvent(event = Event.OnPasswordChange(text = it.toString()))
    }

    private fun setListenerOnConfirmPasswordEditText() =
        binding.confirmPasswordEditText.doAfterTextChanged {
            viewModel.onEvent(event = Event.OnConfirmPasswordChange(text = it.toString()))
        }

    @SuppressLint("ClickableViewAccessibility")
    private fun setListenerOnEyeButton() = with(receiver = binding.passwordEditText) {
        setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val drawableEnd = compoundDrawables[2]
                if (drawableEnd != null) {
                    if (event.rawX >= (right - drawableEnd.bounds.width() - paddingEnd)) {
                        viewModel.onEvent(event = Event.OnEyeClick)
                        return@setOnTouchListener true
                    }
                }
            }
            false
        }
    }

    private fun setListenerOnRegisterButton() = binding.registerButton.setOnClickListener {
        viewModel.onEvent(event = Event.OnRegisterClick)
    }
    /** ========================================================================================= */


    /** ======================================= HANDLERS ======================================== */
    override fun handleStates(state: State) = with(receiver = binding) {
        registerButton.isEnabled = state.isLoading.not()

        val selection = passwordEditText.selectionEnd
        if (state.isPasswordVisible)
            passwordEditText.transformationMethod = HideReturnsTransformationMethod.getInstance()
        else
            passwordEditText.transformationMethod = PasswordTransformationMethod.getInstance()
        passwordEditText.setSelection(selection)
    }

    override fun handleSideEffects(sideEffect: SideEffect) = when (sideEffect) {
        SideEffect.NavigateToHome -> navigateToHomeScreen()
        is SideEffect.ShowError -> binding.registerButton.popMessage(
            text = sideEffect.error.asString(context = requireContext()), color = Colors.amaranth
        )
    }
    /** ========================================================================================= */


    /** ====================================== NAVIGATIONS ====================================== */
    private fun navigateToHomeScreen() {
        val direction = RegisterFragmentDirections.actionRegisterFragmentToHomeFragment()
        findNavController().navigate(directions = direction)
    }
    /** ========================================================================================= */
}
