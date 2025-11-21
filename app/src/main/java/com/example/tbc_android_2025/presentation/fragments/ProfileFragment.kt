package com.example.tbc_android_2025.presentation.fragments

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tbc_android_2025.data.auth.SessionManager
import com.example.tbc_android_2025.presentation.commons.BaseFragment
import com.example.tbc_android_2025.presentation.commons.Strings
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.getValue
import com.example.tbc_android_2025.databinding.FragmentProfileBinding as Binding

@AndroidEntryPoint
class ProfileFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    private val args: ProfileFragmentArgs by navArgs()


    override fun bind() = setEmail(email = args.email)

    override fun listeners() = setListenerOnLogOutButton()


    /** ===================================== AUX =============================================== */
    private fun setEmail(email: String) {
        binding.emailTextView.text = getString(Strings.email_with_format_specifier, email)
    }

    private fun setListenerOnLogOutButton() =
        binding.logOutButton.setOnClickListener { navigateToLoginPage() }

    private fun navigateToLoginPage() = lifecycleScope.launch {
        val direction = ProfileFragmentDirections.actionProfileFragmentToLoginFragment()
        SessionManager.clear(context = requireContext())
        findNavController().navigate(directions = direction)
    }
    /** ========================================================================================= */
}
