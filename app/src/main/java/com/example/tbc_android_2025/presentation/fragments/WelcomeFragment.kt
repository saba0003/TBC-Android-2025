package com.example.tbc_android_2025.presentation.fragments

import androidx.navigation.fragment.findNavController
import com.example.tbc_android_2025.presentation.commons.BaseFragment
import com.example.tbc_android_2025.presentation.commons.Ids
import com.example.tbc_android_2025.databinding.FragmentWelcomeBinding as Binding

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

    private fun navigateToRegisterPage() =
        findNavController().navigate(resId = Ids.action_welcomeFragment_to_registerFragment)

    private fun navigateToLoginPage() =
        findNavController().navigate(resId = Ids.action_welcomeFragment_to_loginFragment)
    /** ========================================================================================= */
}
