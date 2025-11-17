package com.example.tbc_android_2025.presentation.fragments

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tbc_android_2025.data.auth.SessionManager
import com.example.tbc_android_2025.presentation.commons.BaseFragment
import kotlinx.coroutines.launch
import com.example.tbc_android_2025.databinding.FragmentHomeBinding as Binding

class HomeFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    private val args: HomeFragmentArgs by navArgs()


    override fun listeners() = setListenerOnProfileButton()


    /** ===================================== AUX =============================================== */
    private fun setListenerOnProfileButton() =
        binding.profileButton.setOnClickListener { navigateToProfilePage(email = args.user.email) }

    private fun navigateToProfilePage(email: String) = lifecycleScope.launch {
        val direction = HomeFragmentDirections.actionHomeFragmentToProfileFragment(email = email)
        SessionManager.clear(context = requireContext())
        findNavController().navigate(directions = direction)
    }
    /** ========================================================================================= */
}
