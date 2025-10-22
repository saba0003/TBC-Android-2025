package com.example.tbc_android_2025.fragments

import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.tbc_android_2025.commons.BaseFragment
import com.example.tbc_android_2025.commons.Colors
import com.example.tbc_android_2025.commons.Strings
import com.example.tbc_android_2025.user.User
import com.example.tbc_android_2025.user.UserViewModel
import com.example.tbc_android_2025.utils.EmailValidator.validateEmail
import com.example.tbc_android_2025.utils.FragmentKeys.ACTION_KEY
import com.example.tbc_android_2025.utils.FragmentKeys.COLOR_RES_KEY
import com.example.tbc_android_2025.utils.FragmentKeys.MESSAGE_RES_KEY
import com.example.tbc_android_2025.utils.FragmentKeys.RESULT_KEY
import com.example.tbc_android_2025.utils.IntentKeys.ACTION_REMOVED
import com.example.tbc_android_2025.utils.IntentKeys.ACTION_UPDATED
import com.example.tbc_android_2025.databinding.FragmentUpdateUserBinding
import com.example.tbc_android_2025.utils.IntentKeys.EXTRA_ACTIVE_COUNT
import com.example.tbc_android_2025.utils.IntentKeys.EXTRA_DELETED_COUNT
import com.example.tbc_android_2025.utils.IntentKeys.EXTRA_USER
import com.example.tbc_android_2025.utils.popMessage

class UpdateUserFragment : BaseFragment<FragmentUpdateUserBinding>(inflater = FragmentUpdateUserBinding::inflate) {

    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var originalUser: User

    override fun bind() {
        // my SDK is too low to resolve deprecation warning (mine - 24, requires - 33)
        originalUser = requireArguments().getParcelable(EXTRA_USER)!!
        val active = requireArguments().getInt(EXTRA_ACTIVE_COUNT, 0)
        val deleted = requireArguments().getInt(EXTRA_DELETED_COUNT, 0)

        bindUserToFields(user = originalUser)

        // disables email editing
        binding.emailEditText.apply {
            setText(originalUser.email)
            isEnabled = false
            isFocusable = false
            isClickable = false
            alpha = 0.8f
        }
        updateCounters(active = active, deleted = deleted)
    }

    override fun listeners() {
        setListenerOnBackButton()
        setListenerOnUpdateButton()
        setListenerOnRemoveButton()
    }

    private fun setListenerOnBackButton() {
        binding.backButton.setOnClickListener { findNavController().popBackStack() }
    }

    private fun setListenerOnUpdateButton() = binding.run {
        updateButton.setOnClickListener {
            if (!allFieldsAreFilledIn())
                return@setOnClickListener

            val updatedUser = getUserFromInput().copy(email = originalUser.email) // keep email
            if (!validateEmail(
                    emailEditText = emailEditText,
                    emailInput = updatedUser.email
                )
            ) return@setOnClickListener

            val replacedExisting =
                userViewModel.updateUser(oldEmail = originalUser.email, newUser = updatedUser)

            val (messageRes, colorRes) = if (replacedExisting)
                Strings.user_updated_successfully_label to Colors.viridian
            else
                Strings.user_added_successfully_label to Colors.viridian

            sendResultAndNavigateBack(action = ACTION_UPDATED, messageRes = messageRes, colorRes = colorRes)
        }
    }

    private fun setListenerOnRemoveButton() {
        binding.removeButton.setOnClickListener {
            val removed = userViewModel.removeUser(email = originalUser.email)
            val (messageRes, colorRes) = if (removed)
                Strings.user_deleted_successfully_label to Colors.viridian
            else
                Strings.user_does_not_exist_label to Colors.amaranth

            sendResultAndNavigateBack(action = ACTION_REMOVED, messageRes = messageRes, colorRes = colorRes)
        }
    }

    private fun sendResultAndNavigateBack(action: String, messageRes: Int, colorRes: Int) = apply {
        parentFragmentManager.setFragmentResult(
            RESULT_KEY,
            bundleOf(
                ACTION_KEY to action,
                MESSAGE_RES_KEY to messageRes,
                COLOR_RES_KEY to colorRes
            )
        )
        findNavController().popBackStack()
    }

    private fun bindUserToFields(user: User) = binding.apply {
        with(receiver = user) {
            firstNameEditText.setText(firstName)
            lastNameEditText.setText(lastName)
            ageEditText.setText(age.toString())
            emailEditText.setText(email)
        }
    }

    private fun getUserFromInput(): User = binding.run {
        User(
            firstName = firstNameEditText.text.toString().trim(),
            lastName = lastNameEditText.text.toString().trim(),
            age = ageEditText.text.toString().trim().toInt(),
            email = emailEditText.text.toString().trim()
        )
    }

    private fun allFieldsAreFilledIn(): Boolean = binding.run {
        val fields = listOf(firstNameEditText, lastNameEditText, ageEditText, emailEditText)
        val allFilled = fields.none { it.text.isNullOrBlank() }
        if (!allFilled) root.popMessage(
            resId = Strings.all_fields_must_be_filled_in_label,
            color = Colors.amaranth
        )
        return allFilled
    }

    private fun updateCounters(active: Int, deleted: Int) = binding.run {
        activeUsersTextView.text = getString(Strings.active_users_label, active)
        deletedUsersTextView.text = getString(Strings.deleted_users_label, deleted)
    }
}
