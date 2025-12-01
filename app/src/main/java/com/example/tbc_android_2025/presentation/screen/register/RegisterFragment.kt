package com.example.tbc_android_2025.presentation.screen.register

import androidx.fragment.app.viewModels
import com.example.tbc_android_2025.commons.Colors
import com.example.tbc_android_2025.commons.Strings
import com.example.tbc_android_2025.databinding.FragmentRegisterBinding as Binding
import com.example.tbc_android_2025.presentation.commons.BaseFragment
import com.example.tbc_android_2025.presentation.extensions.launchAndRepeatOnStart
import com.example.tbc_android_2025.presentation.extensions.popMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class RegisterFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    private val registerViewModel: RegisterViewModel by viewModels()


    override fun bind() {
        collectObservers()
    }

    override fun listeners() {
        setListenerOnRegisterButton()
        setListenerOnBackButton()
    }


    /** ===================================== AUX =============================================== */
    private fun setListenerOnRegisterButton() = with(receiver = binding) {
        registerButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val repeatPassword = repeatPasswordEditText.text.toString().trim()

            val registerRequest = RegisterRequest(
                email = email,
                password = password,
                repeatedPassword = repeatPassword
            )

            registerViewModel.onEvent(event = RegisterEvent.RegisterUser(request = registerRequest))
        }
    }

    private fun setListenerOnBackButton() = binding.backButton.setOnClickListener { navigateBack() }

    private fun observer() = registerViewModel.state

    private fun collectObservers() = viewLifecycleOwner.launchAndRepeatOnStart {
        observer().collectLatest { handleState(state = it) }
    }

    private fun handleState(state: RegisterState) = with(receiver = state) {
        when {
            isSuccess -> {
                binding.registerButton.popMessage(
                    text = getString(Strings.registration_successful),
                    color = Colors.viridian
                )
                navigateBack()
            }

            error != null -> {
                binding.registerButton.popMessage(text = error, color = Colors.amaranth)
                clearInputFields()
            }

            isLoading -> Unit
        }
    }

    private fun clearInputFields() = with(receiver = binding) {
        emailEditText.setText("")
        passwordEditText.setText("")
        repeatPasswordEditText.setText("")
    }
    /** ========================================================================================= */
}
