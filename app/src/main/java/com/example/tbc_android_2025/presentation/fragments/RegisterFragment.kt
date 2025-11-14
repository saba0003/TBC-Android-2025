package com.example.tbc_android_2025.presentation.fragments

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tbc_android_2025.data.auth.exceptions.RegistrationException
import com.example.tbc_android_2025.presentation.commons.BaseFragment
import com.example.tbc_android_2025.presentation.commons.Colors
import com.example.tbc_android_2025.presentation.commons.Ids
import com.example.tbc_android_2025.presentation.commons.Strings
import com.example.tbc_android_2025.presentation.extensions.popMessage
import com.example.tbc_android_2025.presentation.fragments.user.User
import com.example.tbc_android_2025.presentation.fragments.user.UserViewModel
import kotlin.onFailure
import kotlin.onSuccess
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
                registerUser(email = email, username = username, password = password, view = view)
            } catch (e: RegistrationException) {
                showError(
                    view = view,
                    message = e.message ?: getString(Strings.unknown_registration_error)
                )
            } catch (e: Exception) {
                showError(
                    view = view,
                    message = getString(Strings.an_unexpected_error_occurred, e.message)
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

    private fun registerUser(email: String, username: String, password: String, view: View) =
        userViewModel.registerUserRemote(email = email, password = password) { result ->
            result.onSuccess { res ->
                addUserLocally(email = email, username = username, password = password)
                showSuccess(view = view, token = res.token!!)
                navigateToWelcomePage()
            }
            result.onFailure { e ->
                val exception = RegistrationException.RemoteRegistrationFailed(
                    message = e.message ?: getString(Strings.unknown_error)
                )
                showError(view = view, message = exception.message!!)
            }
        }

    private fun addUserLocally(email: String, username: String, password: String) =
        userViewModel.addUser(User(email = email, username = username, password = password))

    private fun showSuccess(view: View, token: String) =
        view.popMessage(
            text = getString(Strings.registered_successfully_token, token),
            color = Colors.viridian
        )

    private fun showError(view: View, message: String) =
        view.popMessage(
            text = message,
            color = Colors.amaranth
        )

    private fun navigateToWelcomePage() =
        findNavController().navigate(resId = Ids.action_registerFragment_to_welcomeFragment)
    /** ========================================================================================= */
}
