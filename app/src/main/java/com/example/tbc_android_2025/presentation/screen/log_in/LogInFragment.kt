package com.example.tbc_android_2025.presentation.screen.log_in

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tbc_android_2025.commons.Colors
import com.example.tbc_android_2025.commons.Strings
import com.example.tbc_android_2025.databinding.FragmentLoginBinding as Binding
import com.example.tbc_android_2025.presentation.commons.BaseFragment
import com.example.tbc_android_2025.presentation.extensions.launchAndRepeatOnStart
import com.example.tbc_android_2025.presentation.extensions.popMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class LogInFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    private val logInViewModel: LogInViewModel by viewModels()


    override fun bind() {
        collectObservers()
    }

    override fun listeners() {
        setListenerOnLogInButton()
        setListenerOnBackButton()
    }


    /** ===================================== AUX =============================================== */
    private fun setListenerOnLogInButton() = with(receiver = binding) {
        logInButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val logInRequest = LogInRequest(email = email, password = password)
            logInViewModel.onEvent(event = LogInEvent.LogInUser(request = logInRequest))
        }
    }

    private fun setListenerOnBackButton() = binding.backButton.setOnClickListener { navigateBack() }

    private fun navigateToHomePage() {
        val direction = LogInFragmentDirections.actionLoginFragmentToHomeFragment()
        findNavController().navigate(directions = direction)
    }

    private fun observer() = logInViewModel.logInState

    private fun collectObservers() = viewLifecycleOwner.launchAndRepeatOnStart {
        observer().collectLatest { handleState(state = it) }
    }

    private fun handleState(state: LogInState) = with(receiver = state) {
        when {
            isSuccess -> {
                binding.logInButton.popMessage(
                    text = getString(Strings.log_in_successful),
                    color = Colors.viridian
                )
                navigateToHomePage()
            }

            error != null -> {
                binding.logInButton.popMessage(text = error, color = Colors.amaranth)
                clearInputFields()
            }

            isLoading -> Unit
        }
    }

    private fun clearInputFields() = with(receiver = binding) {
        emailEditText.setText("")
        passwordEditText.setText("")
    }
    /** ========================================================================================= */
}
