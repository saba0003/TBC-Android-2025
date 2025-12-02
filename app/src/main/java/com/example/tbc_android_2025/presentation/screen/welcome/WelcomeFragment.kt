package com.example.tbc_android_2025.presentation.screen.welcome

import androidx.navigation.fragment.findNavController
import com.example.tbc_android_2025.databinding.FragmentWelcomeBinding
import com.example.tbc_android_2025.presentation.commons.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>(inflater = FragmentWelcomeBinding::inflate) {


    override fun listeners() {
        setListenerOnRegisterButton()
        setListenerOnLogInButton()
    }


    /** ===================================== AUX =============================================== */
    private fun setListenerOnRegisterButton() =
        binding.registerButton.setOnClickListener { navigateToRegisterPage() }

    private fun setListenerOnLogInButton() =
        binding.logInButton.setOnClickListener { navigateToLogInPage() }

    private fun navigateToRegisterPage() {
        val direction = WelcomeFragmentDirections.actionWelcomeFragmentToRegisterFragment()
        findNavController().navigate(directions = direction)
    }

    private fun navigateToLogInPage() {
        val direction = WelcomeFragmentDirections.actionWelcomeFragmentToLogInFragment()
        findNavController().navigate(directions = direction)
    }
    /** ========================================================================================= */
}
