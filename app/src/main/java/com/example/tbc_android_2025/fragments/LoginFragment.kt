package com.example.tbc_android_2025.fragments

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tbc_android_2025.api.exceptions.LoginException
import com.example.tbc_android_2025.commons.BaseFragment
import com.example.tbc_android_2025.commons.Colors
import com.example.tbc_android_2025.commons.Ids
import com.example.tbc_android_2025.commons.Strings
import com.example.tbc_android_2025.extensions.popMessage
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
                if (username.isEmpty() || password.isEmpty())
                    throw LoginException.EmptyFields()

                val localUser = userViewModel.getUser(username = username, password = password)
                    ?: throw LoginException.UserNotFound()

                view.popMessage(
                    text = getString(Strings.login_successful_welcome, localUser.username),
                    color = Colors.viridian
                )

                findNavController().navigate(resId = Ids.action_loginFragment_to_welcomeFragment)

            } catch (e: LoginException) {
                view.popMessage(
                    text = e.message ?: getString(Strings.unknown_login_error),
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
    /** ========================================================================================= */
}
