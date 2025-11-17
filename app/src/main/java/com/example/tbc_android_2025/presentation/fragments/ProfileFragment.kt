package com.example.tbc_android_2025.presentation.fragments

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tbc_android_2025.data.auth.SessionManager
import com.example.tbc_android_2025.presentation.commons.BaseFragment
import com.example.tbc_android_2025.presentation.commons.Ids
import com.example.tbc_android_2025.presentation.commons.Strings
import kotlinx.coroutines.launch
import kotlin.getValue
import com.example.tbc_android_2025.databinding.FragmentProfileBinding as Binding

class ProfileFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    private val args: HomeFragmentArgs by navArgs()


    override fun bind() = setEmail(email = args.user.email)

    override fun listeners() = setListenerOnLogOutButton()


    /** ===================================== AUX =============================================== */
    private fun setEmail(email: String) {
        binding.emailTextView.text = getString(Strings.email_with_format_specifier, email)
    }

    private fun setListenerOnLogOutButton() =
        binding.logOutButton.setOnClickListener { navigateToLoginPage() }

    private fun navigateToLoginPage() = lifecycleScope.launch {
        SessionManager.clear(context = requireContext())
        findNavController().navigate(resId = Ids.action_profileFragment_to_loginFragment)
    }
    /** ========================================================================================= */
}