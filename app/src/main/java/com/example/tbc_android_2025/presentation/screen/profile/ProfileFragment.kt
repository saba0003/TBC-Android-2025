package com.example.tbc_android_2025.presentation.screen.profile

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tbc_android_2025.databinding.FragmentProfileBinding as Binding
import com.example.tbc_android_2025.presentation.common.fragment.BaseMviFragment
import com.example.tbc_android_2025.presentation.screen.profile.ProfileContract.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment :
    BaseMviFragment<Binding, State, SideEffect, ProfileViewModel>(inflater = Binding::inflate) {

    override val viewModel: ProfileViewModel by viewModels()


    override fun listeners() {
        setListenerOnHomeButton()
        setListenerOnLogoutButton()
    }


    /** ======================================= LISTENERS ======================================= */
    private fun setListenerOnHomeButton() = binding.homeButton.setOnClickListener {
        viewModel.onEvent(event = Event.OnHomeClick)
    }

    private fun setListenerOnLogoutButton() = binding.logoutButton.setOnClickListener {
        viewModel.onEvent(event = Event.OnLogoutClick)
    }
    /** ========================================================================================= */


    /** ======================================= HANDLERS ======================================== */
    override fun handleStates(state: State) = with(receiver = binding) {
        homeButton.isEnabled = state.isLoading.not()
        profileButton.isEnabled = state.isLoading.not()
        logoutButton.isEnabled = state.isLoading.not()
    }

    override fun handleSideEffects(sideEffect: SideEffect) = when (sideEffect) {
        SideEffect.NavigateToHome -> navigateToHomeScreen()
        SideEffect.NavigateToWelcome -> navigateToWelcomeScreen()
    }
    /** ========================================================================================= */


    /** ====================================== NAVIGATIONS ====================================== */
    private fun navigateToHomeScreen() {
        val direction = ProfileFragmentDirections.actionProfileFragmentToHomeFragment()
        findNavController().navigate(directions = direction)
    }

    private fun navigateToWelcomeScreen() {
        val direction = ProfileFragmentDirections.actionProfileFragmentToWelcomeFragment()
        findNavController().navigate(directions = direction)
    }
    /** ========================================================================================= */
}