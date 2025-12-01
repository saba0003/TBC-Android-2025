package com.example.tbc_android_2025.presentation.screen.users

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tbc_android_2025.databinding.FragmentHomeBinding as Binding
import com.example.tbc_android_2025.presentation.commons.BaseFragment
import com.example.tbc_android_2025.presentation.extensions.launchAndRepeatOnStart
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    private val homeViewModel: HomeViewModel by viewModels()
    private val userAdapter by lazy { UserAdapter() }


    override fun bind() {
        binding.recyclerView.adapter = userAdapter
        collectObservers()
    }

    override fun listeners() = setListenerOnProfileButton()


    /** ===================================== AUX =============================================== */
    private fun setListenerOnProfileButton() =
        binding.profileButton.setOnClickListener { navigateToProfilePage() }

    private fun navigateToProfilePage() {
        val direction = HomeFragmentDirections.actionHomeFragmentToProfileFragment()
        findNavController().navigate(directions = direction)
    }

    private fun observer() = homeViewModel.homeState

    private fun collectObservers() = viewLifecycleOwner.launchAndRepeatOnStart {
        observer().collectLatest { handleState(state = it) }
    }

    private fun handleState(state: HomeState) = with(receiver = state) {
        when {
            usersPage != null -> {}
            error != null -> {}
            isLoading -> {}
        }
    }
    /** ========================================================================================= */
}