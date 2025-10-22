package com.example.tbc_android_2025.fragments

import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.tbc_android_2025.commons.BaseFragment
import com.example.tbc_android_2025.commons.Colors
import com.example.tbc_android_2025.commons.Ids
import com.example.tbc_android_2025.commons.Strings
import com.example.tbc_android_2025.databinding.FragmentAddUserBinding
import com.example.tbc_android_2025.user.User
import com.example.tbc_android_2025.user.UserViewModel
import com.example.tbc_android_2025.utils.EmailValidator.validateEmail
import com.example.tbc_android_2025.utils.FragmentKeys.ACTION_KEY
import com.example.tbc_android_2025.utils.FragmentKeys.COLOR_RES_KEY
import com.example.tbc_android_2025.utils.FragmentKeys.MESSAGE_RES_KEY
import com.example.tbc_android_2025.utils.FragmentKeys.RESULT_KEY
import com.example.tbc_android_2025.utils.IntentKeys.EXTRA_ACTIVE_COUNT
import com.example.tbc_android_2025.utils.IntentKeys.EXTRA_DELETED_COUNT
import com.example.tbc_android_2025.utils.IntentKeys.EXTRA_USER
import com.example.tbc_android_2025.utils.popMessage

class AddUserFragment : BaseFragment<FragmentAddUserBinding>(inflater = FragmentAddUserBinding::inflate) {

    private val uvm: UserViewModel by activityViewModels()

    override fun bind() {
        updateCounters()
    }

    override fun listeners() {
        setListenerOnAddButton()
        setListenerOnUpdateUserButton()
        setListenerOnViewUserButton()
        setListenerOnFragmentResult()
    }

    private fun setListenerOnAddButton() = binding.run {
        with(receiver = addUserButton) {
            setOnClickListener {
                if (!allFieldsAreFilledIn())
                    return@setOnClickListener
                val user = getUserFromInput()
                if (!validateEmail(emailEditText = emailEditText, emailInput = user.email))
                    return@setOnClickListener

                val added = uvm.addUser(user = user)
                if (added) {
                    updateCounters()
                    clearFields()
                    popMessage(
                        resId = Strings.user_added_successfully_label,
                        color = Colors.viridian
                    )
                } else {
                    popMessage(
                        resId = Strings.user_with_email_already_exists_label,
                        color = Colors.amaranth
                    )
                }
            }
        }
    }

    private fun setListenerOnUpdateUserButton() = binding.run {
        updateButton.setOnClickListener {
            if (uvm.isEmpty()) {
                root.popMessage(
                    resId = Strings.no_users_available_label,
                    color = Colors.amaranth
                )
                return@setOnClickListener
            }
            val randomUser = uvm.getRandomUser()

            val bundle = bundleOf(
                EXTRA_USER to randomUser,
                EXTRA_ACTIVE_COUNT to uvm.activeUsersCounter,
                EXTRA_DELETED_COUNT to uvm.deletedUsersCounter
            )
            findNavController().navigate(resId = Ids.action_addUserFragment_to_updateUserFragment, args = bundle)
        }
    }

    private fun setListenerOnViewUserButton() {
        binding.viewUsersButton.setOnClickListener {
            findNavController().navigate(resId = Ids.action_addUserFragment_to_viewUsersFragment)
        }
    }

    private fun setListenerOnFragmentResult() {
        // listen for results from UpdateFragment
        parentFragmentManager.setFragmentResultListener(
            RESULT_KEY,
            viewLifecycleOwner
        ) { _, bundle ->
            bundle.getString(ACTION_KEY) ?: return@setFragmentResultListener
            val messageRes = bundle.getInt(MESSAGE_RES_KEY, 0)
            val colorRes = bundle.getInt(COLOR_RES_KEY, Colors.amaranth)

            if (messageRes != 0)
                binding.root.popMessage(resId = messageRes, color = colorRes)
        }
    }

    private fun updateCounters() = binding.run {
        activeUsersTextView.text =
            getString(Strings.active_users_label, uvm.activeUsersCounter)
        deletedUsersTextView.text =
            getString(Strings.deleted_users_label, uvm.deletedUsersCounter)
    }

    private fun clearFields() = binding.run {
        firstNameEditText.text?.clear()
        lastNameEditText.text?.clear()
        ageEditText.text?.clear()
        emailEditText.text?.clear()
    }

    private fun allFieldsAreFilledIn(): Boolean = binding.run {
        val fields = listOf(firstNameEditText, lastNameEditText, ageEditText, emailEditText)
        val allFilled = fields.none { it.text.isNullOrBlank() }
        if (!allFilled) root.popMessage(
            resId = Strings.all_fields_must_be_filled_in_label,
            color = Colors.amaranth
        )
        allFilled
    }

    private fun getUserFromInput(): User = binding.run {
        User(
            firstName = firstNameEditText.text.toString().trim(),
            lastName = lastNameEditText.text.toString().trim(),
            age = ageEditText.text.toString().trim().toInt(),
            email = emailEditText.text.toString().trim()
        )
    }
}
