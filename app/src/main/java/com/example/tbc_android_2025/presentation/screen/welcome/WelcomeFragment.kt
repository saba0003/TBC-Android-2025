package com.example.tbc_android_2025.presentation.screen.welcome

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tbc_android_2025.databinding.FragmentWelcomeBinding as Binding
import com.example.tbc_android_2025.presentation.common.BaseMviFragment
import com.example.tbc_android_2025.presentation.screen.welcome.WelcomeContract.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeFragment :
    BaseMviFragment<Binding, State, SideEffect, WelcomeViewModel>(inflater = Binding::inflate) {

    override val viewModel: WelcomeViewModel by viewModels()


    override fun listeners() {
        setListenerOnRegisterButton()
        setListenerOnLoginButton()
    }


    /** ======================================= LISTENERS ======================================= */
    private fun setListenerOnRegisterButton() = binding.registerButton.setOnClickListener {
        viewModel.onEvent(event = Event.OnRegisterClick)
    }

    private fun setListenerOnLoginButton() = binding.loginButton.setOnClickListener {
        viewModel.onEvent(event = Event.OnLoginClick)
    }
    /** ========================================================================================= */


    /** ======================================= HANDLERS ======================================== */
    override fun handleStates(state: State) = with(receiver = binding) {
        registerButton.isEnabled = state.isLoading.not()
        loginButton.isEnabled = state.isLoading.not()
    }

    override fun handleSideEffects(sideEffect: SideEffect) = when (sideEffect) {
        SideEffect.NavigateToRegister -> navigateToRegisterScreen()
        SideEffect.NavigateToLogin -> navigateToLoginScreen()
    }
    /** ========================================================================================= */


    /** ====================================== NAVIGATIONS ====================================== */
    private fun navigateToRegisterScreen() {
        val direction = WelcomeFragmentDirections.actionWelcomeFragmentToRegisterFragment()
        findNavController().navigate(directions = direction)
    }

    private fun navigateToLoginScreen() {
        val direction = WelcomeFragmentDirections.actionWelcomeFragmentToLoginFragment()
        findNavController().navigate(directions = direction)
    }
    /** ========================================================================================= */
}
