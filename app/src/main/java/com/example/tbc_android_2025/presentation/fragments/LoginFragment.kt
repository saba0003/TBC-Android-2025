package com.example.tbc_android_2025.presentation.fragments

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tbc_android_2025.data.auth.exceptions.LoginException
import com.example.tbc_android_2025.presentation.commons.BaseFragment
import com.example.tbc_android_2025.presentation.commons.Colors
import com.example.tbc_android_2025.presentation.commons.Strings
import com.example.tbc_android_2025.presentation.extensions.popMessage
import com.example.tbc_android_2025.data.models.User
import com.example.tbc_android_2025.presentation.ValidationStrings.REQRES_EMAIL_PATTERN
import com.example.tbc_android_2025.presentation.exceptions.ValidationException
import com.example.tbc_android_2025.presentation.view_models.UserViewModel
import com.example.tbc_android_2025.databinding.FragmentLoginBinding as Binding

class LoginFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    private val userViewModel: UserViewModel by viewModels()


    override fun listeners() = setListenerOnLoginButton()


    /** ===================================== AUX =============================================== */
    private fun setListenerOnLoginButton() = with(receiver = binding) {
        loginButton.setOnClickListener { view ->

            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            try {
                validateFields(email = email, password = password)
                val localUser = fetchLocalUser(email = email, password = password)
                remoteLoginUser(user = localUser, view = view)
            } catch (e: LoginException) {
                showError(
                    view = view,
                    message = e.message ?: getString(Strings.unknown_login_error)
                )
            } catch (e: Exception) {
                showError(
                    view = view,
                    message = getString(Strings.an_unexpected_error_occurred, e.message)
                )
            }
        }
    }

    private fun validateFields(email: String, password: String) {
        checkAgainstEmptiness(email = email, password = password)
        validateEmail(email = email)
    }

    private fun checkAgainstEmptiness(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty())
            throw ValidationException.EmptyFields()
    }

    private fun validateEmail(email: String) {
        val emailPattern = Regex(pattern = REQRES_EMAIL_PATTERN)
        if (!emailPattern.matches(input = email))
            throw ValidationException.InvalidEmail()
    }

    private fun fetchLocalUser(email: String, password: String) =
        userViewModel.getUser(email = email, password = password)
            ?: throw LoginException.UserNotFound()


    private fun remoteLoginUser(user: User, view: View) =
        userViewModel.loginUserRemote(email = user.email, password = user.password) { result ->
            result.onSuccess {
                showSuccess(view = view, email = user.email, token = it.token!!)
                navigateToHomePage(user = user)
            }
            result.onFailure {
                throw LoginException.RemoteLoginFailed(message = getString(Strings.unknown_login_error))
            }
        }

    private fun showSuccess(view: View, email: String, token: String) =
        view.popMessage(
            text = getString(Strings.login_successful_welcome_token, email, token),
            color = Colors.viridian
        )

    private fun showError(view: View, message: String) =
        view.popMessage(
            text = message,
            color = Colors.amaranth
        )

    private fun navigateToHomePage(user: User) {
        val direction = LoginFragmentDirections.actionLoginFragmentToHomeFragment(user = user)
        findNavController().navigate(directions = direction)
    }
    /** ========================================================================================= */
}
