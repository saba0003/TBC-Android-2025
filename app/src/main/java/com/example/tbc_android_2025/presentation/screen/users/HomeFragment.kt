package com.example.tbc_android_2025.presentation.screen.users

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbc_android_2025.commons.Colors
import com.example.tbc_android_2025.databinding.FragmentHomeBinding as Binding
import com.example.tbc_android_2025.presentation.commons.BaseFragment
import com.example.tbc_android_2025.presentation.extensions.launchAndRepeatOnStart
import com.example.tbc_android_2025.presentation.extensions.popMessage
import com.example.tbc_android_2025.presentation.screen.users.HomeEvent.GetUsers
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

// TODO: Paging to be added
@AndroidEntryPoint
class HomeFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    private val homeViewModel: HomeViewModel by viewModels()
    private val userAdapter by lazy { UserAdapter() }
    private val args: HomeFragmentArgs by navArgs()


    override fun bind() {
        binding.recyclerView.adapter = userAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        homeViewModel.onEvent(event = GetUsers(page = args.page))
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

    private fun observer() = homeViewModel.state

    private fun collectObservers() = viewLifecycleOwner.launchAndRepeatOnStart {
        observer().collectLatest { handleState(state = it) }
    }

    private fun handleState(state: HomeState) = with(receiver = state) {
        when {
            usersPage != null -> userAdapter.submitList(usersPage.data)
            error != null -> binding.root.popMessage(text = error, color = Colors.amaranth)
            isLoading -> Unit
        }
    }
    /** ========================================================================================= */
}