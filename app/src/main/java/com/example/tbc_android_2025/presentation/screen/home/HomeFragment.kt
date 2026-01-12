package com.example.tbc_android_2025.presentation.screen.home

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbc_android_2025.databinding.FragmentHomeBinding as Binding
import com.example.tbc_android_2025.presentation.common.fragment.BaseMviFragment
import com.example.tbc_android_2025.presentation.common.Colors
import com.example.tbc_android_2025.presentation.extension.asString
import com.example.tbc_android_2025.presentation.extension.launchAndRepeatOnStart
import com.example.tbc_android_2025.presentation.extension.popMessage
import com.example.tbc_android_2025.presentation.screen.home.HomeContract.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeFragment :
    BaseMviFragment<Binding, State, SideEffect, HomeViewModel>(inflater = Binding::inflate) {

    override val viewModel: HomeViewModel by viewModels()
    private val adapter by lazy { HomeAdapter() }


    override fun bind() {
        setupRecycler()
        collectUsers()
    }

    override fun listeners() = setListenerOnProfileButton()


    /** ======================================= LISTENERS ======================================= */
    private fun setListenerOnProfileButton() = binding.profileButton.setOnClickListener {
        viewModel.onEvent(event = Event.OnProfileClick)
    }
    /** ========================================================================================= */


    /** ======================================= HANDLERS ======================================== */
    override fun handleStates(state: State) = with(receiver = binding) {
        homeButton.isEnabled = state.isLoading.not()
        profileButton.isEnabled = state.isLoading.not()
    }

    override fun handleSideEffects(sideEffect: SideEffect) = when (sideEffect) {
        SideEffect.NavigateToProfile -> navigateToProfileScreen()
        is SideEffect.ShowError -> binding.root.popMessage(
            text = sideEffect.error.asString(context = requireContext()),
            color = Colors.amaranth
        )
    }
    /** ========================================================================================= */


    /** ====================================== NAVIGATIONS ====================================== */
    private fun navigateToProfileScreen() {
        val direction = HomeFragmentDirections.actionHomeFragmentToProfileFragment()
        findNavController().navigate(directions = direction)
    }
    /** ========================================================================================= */


    /** ========================================== AUX ========================================== */
    private fun setupRecycler() = with(receiver = binding.recyclerView) {
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(requireContext())
        adapter = this@HomeFragment.adapter
    }

    private fun collectUsers() = viewLifecycleOwner.launchAndRepeatOnStart {
        viewModel.usersFlow.collectLatest { adapter.submitData(pagingData = it) }
    }
    /** ========================================================================================= */
}