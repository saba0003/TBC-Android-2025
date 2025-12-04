package com.example.tbc_android_2025.presentation.screen.splash

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tbc_android_2025.databinding.FragmentSplashBinding as Binding
import com.example.tbc_android_2025.presentation.commons.BaseFragment
import com.example.tbc_android_2025.presentation.extensions.launchAndRepeatOnStart
import com.example.tbc_android_2025.presentation.screen.splash.SplashState.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    private val viewModel: SplashViewModel by viewModels()


    override fun bind() = observeStates()


    /** ===================================== AUX =============================================== */
    private fun observeStates() {
        viewLifecycleOwner.launchAndRepeatOnStart {
            viewModel.state.collect {
                when (it) {
                    Loading -> Unit
                    NavigateToTemplate -> navigateToTemplateFragment()
                }
            }
        }
    }

    private fun navigateToTemplateFragment() {
        val direction = SplashFragmentDirections.actionSplashFragmentToTemplateFragment()
        findNavController().navigate(directions = direction)
    }
    /** ========================================================================================= */


    override fun onPause() {
        super.onPause()
        viewModel.onEvent(event = SplashEvent.OnStopSplash)
    }
}
