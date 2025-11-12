package com.example.tbc_android_2025.fragments

import androidx.navigation.fragment.findNavController
import com.example.tbc_android_2025.commons.BaseFragment
import com.example.tbc_android_2025.commons.Ids
import com.example.tbc_android_2025.databinding.FragmentWelcomeBinding as Binding

class WelcomeFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    override fun listeners() {
        setListenerOnRegisterButton()
        setListenerOnLoginButton()
    }


    /** ===================================== AUX =============================================== */
    private fun setListenerOnRegisterButton() = binding.registerButton.setOnClickListener {
        findNavController().navigate(resId = Ids.action_welcomeFragment_to_registerFragment)
    }

    private fun setListenerOnLoginButton() = binding.loginButton.setOnClickListener {
        findNavController().navigate(resId = Ids.action_welcomeFragment_to_loginFragment)
    }
    /** ========================================================================================= */
}
