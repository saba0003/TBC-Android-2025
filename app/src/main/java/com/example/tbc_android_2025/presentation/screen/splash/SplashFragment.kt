package com.example.tbc_android_2025.presentation.screen.splash

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tbc_android_2025.databinding.FragmentSplashBinding as Binding
import com.example.tbc_android_2025.presentation.commons.BaseFragment
import com.example.tbc_android_2025.presentation.extensions.launchAndRepeatOnStart
import com.example.tbc_android_2025.presentation.screen.splash.SplashState.*
import com.example.tbc_android_2025.presentation.screen.splash.SplashSideEffect.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    private val viewModel: SplashViewModel by viewModels()


    override fun bind() = collectObservers()


    /** ======================================= OBSERVERS ======================================= */
    private fun observeStates() = viewModel.state

    private fun observeSideEffects() = viewModel.sideEffect

    private fun collectObservers() {
        viewLifecycleOwner.launchAndRepeatOnStart {
            launch { observeStates().collect { handleStates(group = it) } }
            launch { observeSideEffects().collect { handleSideEffects(group = it) } }
        }
    }
    /** ========================================================================================= */


    /** ======================================= HANDLERS ======================================== */
    private fun handleStates(group: SplashState) = when (group) {
        is Loading -> Unit
        is Finished -> Unit
    }

    private fun handleSideEffects(group: SplashSideEffect) = when (group) {
        is NavigateToTemplate -> navigateToTemplateFragment()
    }
    /** ========================================================================================= */


    /** ========================================== AUX ========================================== */
    private fun navigateToTemplateFragment() {
        val direction = SplashFragmentDirections.actionSplashFragmentToTemplateFragment()
        findNavController().navigate(directions = direction)
    }
    /** ========================================================================================= */
}
