package com.example.tbc_android_2025.fragments

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tbc_android_2025.api.exceptions.LoginException
import com.example.tbc_android_2025.commons.BaseFragment
import com.example.tbc_android_2025.commons.Colors
import com.example.tbc_android_2025.commons.Ids
import com.example.tbc_android_2025.commons.Strings
import com.example.tbc_android_2025.extensions.popMessage
import com.example.tbc_android_2025.user.User
import com.example.tbc_android_2025.user.UserViewModel
import kotlin.getValue
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
                showSuccess(view = view, user = localUser)
                navigateToWelcome()
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

    private fun showSuccess(view: View, user: User) =
        view.popMessage(
            text = getString(Strings.login_successful_welcome, user.username),
            color = Colors.viridian
        )

    private fun showError(view: View, message: String) =
        view.popMessage(
            text = message,
            color = Colors.amaranth
        )

    private fun navigateToWelcome() =
        findNavController().navigate(resId = Ids.action_loginFragment_to_welcomeFragment)
    /** ========================================================================================= */
}
