package com.example.tbc_android_2025.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.tbc_android_2025.R
import com.example.tbc_android_2025.models.User
import com.example.tbc_android_2025.models.UserViewModel
import com.example.tbc_android_2025.utils.EmailValidator.validateEmail
import com.example.tbc_android_2025.utils.FragmentKeys.ACTION_KEY
import com.example.tbc_android_2025.utils.FragmentKeys.COLOR_RES_KEY
import com.example.tbc_android_2025.utils.FragmentKeys.MESSAGE_RES_KEY
import com.example.tbc_android_2025.utils.FragmentKeys.RESULT_KEY
import com.example.tbc_android_2025.utils.IntentKeys.ACTION_REMOVED
import com.example.tbc_android_2025.utils.IntentKeys.ACTION_UPDATED
import com.example.tbc_android_2025.databinding.FragmentUpdateBinding
import com.example.tbc_android_2025.utils.IntentKeys
import com.example.tbc_android_2025.utils.popMessage

class UpdateFragment : Fragment() {
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var originalUser: User

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout =
        FragmentUpdateBinding
            .inflate(inflater, container, false)
            .also { _binding = it }
            .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        // my SDK is too low to resolve deprecation warning (mine - 24, requires - 33)
        originalUser = requireArguments().getParcelable(IntentKeys.EXTRA_USER)!!
        val active = requireArguments().getInt(IntentKeys.EXTRA_ACTIVE_COUNT, 0)
        val deleted = requireArguments().getInt(IntentKeys.EXTRA_DELETED_COUNT, 0)

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

        setListenerOnBackButton()
        setListenerOnUpdateButton()
        setListenerOnRemoveButton()
    }

    private fun setListenerOnBackButton() {
        binding.backButton.setOnClickListener { findNavController().popBackStack() }
    }

    private fun setListenerOnUpdateButton() = binding.run {
        updateButton.setOnClickListener {
            if (!allFieldsAreFilledIn()) return@setOnClickListener

            val updatedUser = getUserFromInput().copy(email = originalUser.email) // keep email
            if (!validateEmail(
                    binding = binding,
                    email = updatedUser.email
                )
            ) return@setOnClickListener

            val replacedExisting =
                userViewModel.updateUser(oldEmail = originalUser.email, newUser = updatedUser)

            val (messageRes, colorRes) = if (replacedExisting)
                R.string.user_updated_successfully_label to R.color.viridian
            else
                R.string.user_added_successfully_label to R.color.viridian

            sendResultAndNavigateBack(action = ACTION_UPDATED, messageRes = messageRes, colorRes = colorRes)
        }
    }

    private fun setListenerOnRemoveButton() {
        binding.removeButton.setOnClickListener {
            val removed = userViewModel.removeUser(email = originalUser.email)
            val (messageRes, colorRes) = if (removed)
                R.string.user_deleted_successfully_label to R.color.viridian
            else
                R.string.user_does_not_exist_label to R.color.amaranth

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
            resId = R.string.all_fields_must_be_filled_in_label,
            color = R.color.amaranth
        )
        return allFilled
    }

    private fun updateCounters(active: Int, deleted: Int) = binding.run {
        activeUsers.text = getString(R.string.active_users_label, active)
        deletedUsers.text = getString(R.string.deleted_users_label, deleted)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
