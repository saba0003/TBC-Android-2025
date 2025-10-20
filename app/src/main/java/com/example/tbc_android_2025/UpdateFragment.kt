package com.example.tbc_android_2025

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.tbc_android_2025.EmailValidator.validateEmail
import com.example.tbc_android_2025.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment() {
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var originalUser: User

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentUpdateBinding.inflate(inflater, container, false).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        originalUser = requireArguments().getParcelable(IntentKeys.EXTRA_USER)!!
        val active = requireArguments().getInt(IntentKeys.EXTRA_ACTIVE_COUNT, 0)
        val deleted = requireArguments().getInt(IntentKeys.EXTRA_DELETED_COUNT, 0)

        bindUserToFields(originalUser)
        // disable email editing
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

            // Prepare result for MainFragment (messageRes and color)
            val (messageRes, colorRes) = if (replacedExisting)
                R.string.user_updated_successfully_label to R.color.viridian
            else
                R.string.user_added_successfully_label to R.color.viridian

            // Notify MainFragment via FragmentResult
            parentFragmentManager.setFragmentResult(
                "updateResult",
                bundleOf(
                    "action" to IntentKeys.ACTION_UPDATED,
                    "messageRes" to messageRes,
                    "colorRes" to colorRes
                )
            )

            findNavController().popBackStack()
        }
    }

    private fun setListenerOnRemoveButton() {
        binding.removeButton.setOnClickListener {
            val removed = userViewModel.removeUser(email = originalUser.email)
            val (messageRes, colorRes) = if (removed)
                R.string.user_deleted_successfully_label to R.color.viridian
            else
                R.string.user_does_not_exist_label to R.color.amaranth
            parentFragmentManager.setFragmentResult(
                "updateResult",
                bundleOf(
                    "action" to IntentKeys.ACTION_REMOVED,
                    "messageRes" to messageRes,
                    "colorRes" to colorRes
                )
            )
            findNavController().popBackStack()
        }
    }

    private fun bindUserToFields(user: User) = binding.apply {
        firstNameEditText.setText(user.firstName)
        lastNameEditText.setText(user.lastName)
        ageEditText.setText(user.age.toString())
        emailEditText.setText(user.email)
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
