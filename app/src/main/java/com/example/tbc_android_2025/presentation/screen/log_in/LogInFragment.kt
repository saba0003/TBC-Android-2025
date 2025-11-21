package com.example.tbc_android_2025.presentation.screen.log_in

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.tbc_android_2025.databinding.FragmentLoginBinding
import com.example.tbc_android_2025.presentation.commons.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LogInFragment : BaseFragment<FragmentLoginBinding>(inflater = FragmentLoginBinding::inflate) {

    private val viewModel: LogInViewModel by viewModels()


    override fun listeners() = setListenerOnLoginButton()

    override fun observes() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                viewModel.sideEffect.collect {
                    when (it) {

                    }
                }
            }
        }
    }

    private fun setListenerOnLoginButton() = with(receiver = binding) {
        loginButton.setOnClickListener {
            viewModel.onEvent(
                event = LogInEvent.LogIn(
                    email = emailEditText.text.toString().trim(),
                    password = passwordEditText.text.toString().trim()
                )
            )
        }
    }

    private fun handleLoader(loader: Boolean) {
        binding.progressBar.isVisible = loader
    }
}
