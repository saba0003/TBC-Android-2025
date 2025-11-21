package com.example.tbc_android_2025.presentation.fragments

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tbc_android_2025.data.auth.exceptions.ExceptionStrings.USER_NOT_FOUND_ERR
import com.example.tbc_android_2025.data.auth.exceptions.RegistrationException
import com.example.tbc_android_2025.presentation.commons.BaseFragment
import com.example.tbc_android_2025.presentation.commons.Colors
import com.example.tbc_android_2025.presentation.commons.Strings
import com.example.tbc_android_2025.presentation.extensions.popMessage
import com.example.tbc_android_2025.data.models.User
import com.example.tbc_android_2025.presentation.ValidationStrings.REQRES_EMAIL_PATTERN
import com.example.tbc_android_2025.presentation.exceptions.ValidationException
import com.example.tbc_android_2025.presentation.view_models.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.onFailure
import kotlin.onSuccess
import com.example.tbc_android_2025.databinding.FragmentRegisterBinding as Binding

@AndroidEntryPoint
class RegisterFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    private val userViewModel: UserViewModel by viewModels()


    override fun listeners() = setListenerOnRegisterButton()


    /** ===================================== AUX =============================================== */
    private fun setListenerOnRegisterButton() = with(receiver = binding) {
        registerButton.setOnClickListener { view ->
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val repeatPassword = repeatPasswordEditText.text.toString().trim()

            try {
                validateFields(email = email, password = password, repeatPassword = repeatPassword)
                registerUser(email = email, password = password, view = view)
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

    private fun validateFields(email: String, password: String, repeatPassword: String) {
        checkAgainstEmptiness(email = email, password = password, repeatPassword = repeatPassword)
        validateEmail(email = email)
        validatePassword(password = password, repeatPassword = repeatPassword)
    }

    private fun checkAgainstEmptiness(email: String, password: String, repeatPassword: String) {
        if (email.isEmpty() || password.isEmpty() || repeatPassword.isEmpty())
            throw ValidationException.EmptyFields()
    }

    private fun validateEmail(email: String) {
        val emailPattern = Regex(pattern = REQRES_EMAIL_PATTERN)
        if (!emailPattern.matches(input = email))
            throw ValidationException.InvalidEmail()
    }

    private fun validatePassword(password: String, repeatPassword: String) {
        if (password != repeatPassword)
            throw ValidationException.PasswordsMismatch()
    }

    private fun registerUser(email: String, password: String, view: View) =
        userViewModel.registerUserRemote(email = email, password = password) { result ->
            result.onSuccess { res ->
                addUserLocally(email = email, password = password)
                showSuccess(view = view)
                navigateToWelcomePage()
            }
            result.onFailure { e ->
                val exception = RegistrationException.RemoteRegistrationFailed(
                    message = e.message ?: USER_NOT_FOUND_ERR
                )
                showError(view = view, message = exception.message!!)
            }
        }

    private fun addUserLocally(email: String, password: String) =
        userViewModel.addUser(User(email = email, password = password))

    private fun showSuccess(view: View) =
        view.popMessage(
            text = getString(Strings.registered_successfully),
            color = Colors.viridian
        )

    private fun showError(view: View, message: String) =
        view.popMessage(
            text = message,
            color = Colors.amaranth
        )

    private fun navigateToWelcomePage() {
        val direction = RegisterFragmentDirections.actionRegisterFragmentToWelcomeFragment()
        findNavController().navigate(directions = direction)
    }
    /** ========================================================================================= */
}
