package com.example.tbc_android_2025.presentation.screens.home

import androidx.fragment.app.viewModels
import com.example.tbc_android_2025.databinding.FragmentHomeBinding as Binding
import com.example.tbc_android_2025.presentation.commons.BaseFragment
import com.example.tbc_android_2025.presentation.extensions.launchAndRepeatOnStart
import com.example.tbc_android_2025.presentation.screens.home.post.PostContract.State
import com.example.tbc_android_2025.presentation.screens.home.post.PostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    private val viewModel: PostViewModel by viewModels()


    override fun bind() = collectObservers()


    /** ======================================= OBSERVERS ======================================= */
    private fun observeStates() = viewModel.state

    private fun collectObservers() {
        viewLifecycleOwner.launchAndRepeatOnStart {
            observeStates().collect { handleStates(group = it) }
        }
    }
    /** ========================================================================================= */


    /** ======================================= HANDLERS ======================================== */
    private fun handleStates(group: State) {}
    /** ========================================================================================= */
}
