package com.example.tbc_android_2025.presentation.screen.login

import android.annotation.SuppressLint
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.MotionEvent
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tbc_android_2025.databinding.FragmentLoginBinding as Binding
import com.example.tbc_android_2025.presentation.common.BaseMviFragment
import com.example.tbc_android_2025.presentation.common.Colors
import com.example.tbc_android_2025.presentation.extension.asString
import com.example.tbc_android_2025.presentation.extension.popMessage
import com.example.tbc_android_2025.presentation.screen.login.LoginContract.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment :
    BaseMviFragment<Binding, State, SideEffect, LoginViewModel>(inflater = Binding::inflate) {

    override val viewModel: LoginViewModel by viewModels()


    override fun listeners() {
        setListenerOnEmailEditText()
        setListenerOnPasswordEditText()
        setListenerOnEyeButton()
        setListenerOnRememberMeCheckBox()
        setListenerOnLoginButton()
    }


    /** ======================================= LISTENERS ======================================= */
    private fun setListenerOnEmailEditText() = binding.emailEditText.doAfterTextChanged {
        viewModel.onEvent(event = Event.OnEmailChange(text = it.toString()))
    }

    private fun setListenerOnPasswordEditText() = binding.passwordEditText.doAfterTextChanged {
        viewModel.onEvent(event = Event.OnPasswordChange(text = it.toString()))
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

    private fun setListenerOnRememberMeCheckBox() = binding.rememberMeCheckBox.setOnClickListener {
        viewModel.onEvent(event = Event.OnRememberMeClick)
    }

    private fun setListenerOnLoginButton() = binding.loginButton.setOnClickListener {
        viewModel.onEvent(event = Event.OnLoginClick)
    }
    /** ========================================================================================= */


    /** ======================================= HANDLERS ======================================== */
    override fun handleStates(state: State) = with(receiver = binding) {
        loginButton.isEnabled = state.isLoading.not()

        val selection = passwordEditText.selectionEnd
        if (state.isPasswordVisible)
            passwordEditText.transformationMethod = HideReturnsTransformationMethod.getInstance()
        else
            passwordEditText.transformationMethod = PasswordTransformationMethod.getInstance()
        passwordEditText.setSelection(selection)

        if (rememberMeCheckBox.isChecked != state.rememberMe)
            rememberMeCheckBox.isChecked = state.rememberMe
    }

    override fun handleSideEffects(sideEffect: SideEffect) = when (sideEffect) {
        SideEffect.NavigateToHome -> navigateToHomeScreen()
        is SideEffect.ShowError -> binding.loginButton.popMessage(
            text = sideEffect.error.asString(context = requireContext()), color = Colors.amaranth
        )
    }
    /** ========================================================================================= */


    /** ====================================== NAVIGATIONS ====================================== */
    private fun navigateToHomeScreen() {
        val direction = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
        findNavController().navigate(directions = direction)
    }
    /** ========================================================================================= */
}
