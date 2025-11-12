package com.example.tbc_android_2025.fragments

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tbc_android_2025.api.exceptions.RegistrationException
import com.example.tbc_android_2025.commons.BaseFragment
import com.example.tbc_android_2025.commons.Colors
import com.example.tbc_android_2025.commons.Ids
import com.example.tbc_android_2025.commons.Strings
import com.example.tbc_android_2025.extensions.popMessage
import com.example.tbc_android_2025.user.User
import com.example.tbc_android_2025.user.UserViewModel
import kotlin.getValue
import com.example.tbc_android_2025.databinding.FragmentRegisterBinding as Binding

class RegisterFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    private val userViewModel: UserViewModel by viewModels()


    override fun listeners() = setListenerOnRegisterButton()


    /** ===================================== AUX =============================================== */
    private fun setListenerOnRegisterButton() = with(receiver = binding) {
        registerButton.setOnClickListener { view ->

            val email = emailEditText.text.toString().trim()
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            try {
                validateFields(email = email, username = username, password = password)
                validateEmail(email = email)

                userViewModel.registerUserRemote(email = email, password = password) { result ->
                    result.onSuccess { res ->

                        userViewModel.addUser(
                            User(
                                email = email,
                                username = username,
                                password = password
                            )
                        )

                        view.popMessage(
                            text = getString(Strings.registered_successfully_token, res.token),
                            color = Colors.viridian
                        )

                        findNavController().navigate(resId = Ids.action_registerFragment_to_welcomeFragment)
                    }
                    result.onFailure { e ->
                        val exception = RegistrationException.RemoteRegistrationFailed(
                            message = e.message ?: getString(Strings.unknown_error)
                        )
                        view.popMessage(
                            text = exception.message!!,
                            color = Colors.amaranth
                        )
                    }
                }

            } catch (e: RegistrationException) {
                view.popMessage(
                    text = e.message ?: getString(Strings.unknown_registration_error),
                    color = Colors.amaranth
                )
            } catch (e: Exception) {
                view.popMessage(
                    text = getString(Strings.an_unexpected_error_occurred, e.message),
                    color = Colors.amaranth
                )
            }
        }
    }

    private fun validateFields(email: String, username: String, password: String) {
        if (email.isEmpty() || username.isEmpty() || password.isEmpty())
            throw RegistrationException.EmptyFields()
    }

    private fun validateEmail(email: String) {
        if (email.lowercase() != getString(Strings.required_registration_email))
            throw RegistrationException.InvalidEmail()
    }
    /** ========================================================================================= */
}
