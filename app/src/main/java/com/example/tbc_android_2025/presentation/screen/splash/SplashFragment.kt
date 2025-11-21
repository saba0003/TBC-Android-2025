package com.example.tbc_android_2025.presentation.screen.splash

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.tbc_android_2025.presentation.commons.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.example.tbc_android_2025.databinding.FragmentSplashBinding as Binding

@AndroidEntryPoint
class SplashFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    private val viewModel: SplashViewModel by viewModels()

    override fun bind() {
        viewModel.onEvent(event = )
    }

    override fun listeners() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                viewModel.sideEffect.collect {
                    when (it) {
                        SplashSideEffect.NavigateToHome -> SplashFragment.actionSplashFragmentToUsersFragment()
                        SplashSideEffect.NavigateToLogin -> SplashFragment.actionSplashFragmentToLoginFragment()
                    }
                }
            }
        }
    }

}