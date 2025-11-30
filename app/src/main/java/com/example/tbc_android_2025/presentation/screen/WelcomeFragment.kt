package com.example.tbc_android_2025.presentation.screen

import androidx.navigation.fragment.findNavController
import com.example.tbc_android_2025.presentation.commons.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import com.example.tbc_android_2025.databinding.FragmentWelcomeBinding as Binding

@AndroidEntryPoint
class WelcomeFragment : BaseFragment<Binding>(inflater = Binding::inflate) {


    override fun listeners() {
        setListenerOnRegisterButton()
        setListenerOnLoginButton()
    }


    /** ===================================== AUX =============================================== */
    private fun setListenerOnRegisterButton() =
        binding.registerButton.setOnClickListener { navigateToRegisterPage() }

    private fun setListenerOnLoginButton() =
        binding.loginButton.setOnClickListener { navigateToLoginPage() }

    private fun navigateToRegisterPage() {
        val direction = WelcomeFragmentDirections.actionWelcomeFragmentToRegisterFragment()
        findNavController().navigate(directions = direction)
    }

    private fun navigateToLoginPage() {
        val direction = WelcomeFragmentDirections.actionWelcomeFragmentToLogInFragment()
        findNavController().navigate(directions = direction)
    }
    /** ========================================================================================= */
}
