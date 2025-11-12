package com.example.tbc_android_2025.fragments

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tbc_android_2025.commons.BaseFragment
import com.example.tbc_android_2025.commons.Colors
import com.example.tbc_android_2025.commons.Ids
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

            val email = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                view.popMessage(
                    text = "Please fill all fields!",
                    color = Colors.amaranth
                )
                return@setOnClickListener
            }

            userViewModel.loginUserRemote(email = email, password = password) { result ->
                result.onSuccess { res ->

                    val localUser = userViewModel.getUser(username = email, password = password)

                    if (localUser != null) {
                        view.popMessage(
                            text = "Login Successful! Welcome ${localUser.username}",
                            color = Colors.light_green
                        )
                    } else {
                        view.popMessage(
                            text = "Login Successful!",
                            color = Colors.light_green
                        )
                    }

                    findNavController().navigate(resId = Ids.action_loginFragment_to_welcomeFragment)
                }

                result.onFailure { e ->
                    view.popMessage(
                        text = "Login failed: ${e.message}",
                        color = Colors.amaranth
                    )
                }
            }
        }
    }
    /** ========================================================================================= */
}
