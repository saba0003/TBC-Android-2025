package com.example.tbc_android_2025.presentation.fragments

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tbc_android_2025.data.auth.SessionManager
import com.example.tbc_android_2025.presentation.commons.BaseFragment
import com.example.tbc_android_2025.presentation.commons.Ids
import com.example.tbc_android_2025.presentation.commons.Strings
import com.example.tbc_android_2025.presentation.fragments.user.User
import com.example.tbc_android_2025.databinding.FragmentHomeBinding as Binding

class HomeFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    private val args: HomeFragmentArgs by navArgs()


    override fun bind() = setTextViews(user = args.user)

    override fun listeners() = setListenerOnLogOutButton()


    /** ===================================== AUX =============================================== */
    private fun setTextViews(user: User) = with(receiver = binding) {
        with(receiver = user) {
            idTextView.text = getString(Strings.id_with_format_specifier, id)
            emailTextView.text = getString(Strings.email_with_format_specifier, email)
            usernameTextView.text = getString(Strings.username_with_format_specifier, username)
            passwordTextView.text = getString(Strings.password_with_format_specifier, password)
        }
    }

    private fun setListenerOnLogOutButton() =
        binding.logOutButton.setOnClickListener { navigateToWelcomePage() }

    private fun navigateToWelcomePage() {
        SessionManager.clear()
        findNavController().navigate(resId = Ids.action_homeFragment_to_welcomeFragment)
    }
    /** ========================================================================================= */
}
