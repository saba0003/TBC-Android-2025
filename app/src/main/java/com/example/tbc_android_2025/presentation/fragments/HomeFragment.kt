package com.example.tbc_android_2025.presentation.fragments

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tbc_android_2025.data.auth.SessionManager
import com.example.tbc_android_2025.presentation.UserAdapter
import com.example.tbc_android_2025.presentation.commons.BaseFragment
import com.example.tbc_android_2025.presentation.view_models.HomeViewModel
import kotlinx.coroutines.launch
import com.example.tbc_android_2025.databinding.FragmentHomeBinding as Binding

class HomeFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    private val args: HomeFragmentArgs by navArgs()
    private val viewModel: HomeViewModel by viewModels()
    private val adapter = UserAdapter()


    override fun bind() = with(receiver = binding) {
        recyclerView.adapter = adapter
        observeUsers()
        viewModel.loadUsers()
    }

    override fun listeners() = setListenerOnProfileButton()


    /** ===================================== AUX =============================================== */
    private fun observeUsers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                viewModel.users.collect { users ->
                    adapter.submitList(users)
                }
            }
        }
    }

    private fun setListenerOnProfileButton() =
        binding.profileButton.setOnClickListener { navigateToProfilePage(email = args.user.email) }

    private fun navigateToProfilePage(email: String) = lifecycleScope.launch {
        val direction = HomeFragmentDirections.actionHomeFragmentToProfileFragment(email = email)
        SessionManager.clear(context = requireContext())
        findNavController().navigate(directions = direction)
    }
    /** ========================================================================================= */
}
