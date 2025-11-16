package com.example.tbc_android_2025.presentation.fragments

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tbc_android_2025.data.auth.exceptions.LoginException
import com.example.tbc_android_2025.presentation.commons.BaseFragment
import com.example.tbc_android_2025.presentation.commons.Colors
import com.example.tbc_android_2025.presentation.commons.Strings
import com.example.tbc_android_2025.presentation.extensions.popMessage
import com.example.tbc_android_2025.presentation.fragments.user.User
import com.example.tbc_android_2025.presentation.fragments.user.UserViewModel
import com.example.tbc_android_2025.databinding.FragmentLoginBinding as Binding

class LoginFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    private val userViewModel: UserViewModel by viewModels()


    override fun listeners() = setListenerOnLoginButton()


    /** ===================================== AUX =============================================== */
    private fun setListenerOnLoginButton() = with(receiver = binding) {
        loginButton.setOnClickListener { view ->

            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            try {
                validateLoginFields(username = username, password = password)
                val localUser = fetchLocalUser(username = username, password = password)
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

    private fun validateLoginFields(username: String, password: String) {
        if (username.isEmpty() || password.isEmpty())
            throw LoginException.EmptyFields()
    }

    private fun fetchLocalUser(username: String, password: String) =
        userViewModel.getUser(username = username, password = password)
            ?: throw LoginException.UserNotFound()


    private fun remoteLoginUser(user: User, view: View) =
        userViewModel.loginUserRemote(email = user.email, password = user.password) { result ->
            result.onSuccess {
                showSuccess(view = view, username = user.username, token = it.token!!)
                navigateToHomePage(user = user, token = it.token)
            }
            result.onFailure {
                throw LoginException.RemoteLoginFailed(message = getString(Strings.unknown_login_error))
            }
        }

    private fun showSuccess(view: View, username: String, token: String) =
        view.popMessage(
            text = getString(Strings.login_successful_welcome_token, username, token),
            color = Colors.viridian
        )

    private fun showError(view: View, message: String) =
        view.popMessage(
            text = message,
            color = Colors.amaranth
        )

    private fun navigateToHomePage(user: User, token: String) {
        val direction =
            LoginFragmentDirections.actionLoginFragmentToHomeFragment(user = user, token = token)
        findNavController().navigate(directions = direction)
    }
    /** ========================================================================================= */
}
