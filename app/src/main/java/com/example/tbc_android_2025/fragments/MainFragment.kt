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
import com.example.tbc_android_2025.databinding.FragmentMainBinding
import com.example.tbc_android_2025.utils.IntentKeys
import com.example.tbc_android_2025.utils.popMessage

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout =
        FragmentMainBinding
            .inflate(inflater, container, false)
            .also { _binding = it }
            .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        updateCounters()
        setListenerOnAddButton()
        setListenerOnOpenUpdatePage()

        // listen for results from UpdateFragment
        parentFragmentManager.setFragmentResultListener(
            RESULT_KEY,
            viewLifecycleOwner
        ) { _, bundle ->
            bundle.getString(ACTION_KEY) ?: return@setFragmentResultListener
            val messageRes = bundle.getInt(MESSAGE_RES_KEY, 0)
            val colorRes = bundle.getInt(COLOR_RES_KEY, R.color.amaranth)

            updateCounters()
            if (messageRes != 0)
                binding.root.popMessage(resId = messageRes, color = colorRes)
        }
    }

    private fun setListenerOnAddButton() = binding.run {
        addButton.setOnClickListener {
            if (!allFieldsAreFilledIn())
                return@setOnClickListener
            val user = getUserFromInput()
            if (!validateEmail(binding = this, email = user.email))
                return@setOnClickListener

            val added = userViewModel.addUser(user = user)
            with(receiver = addButton) {
                if (added) {
                    updateCounters()
                    clearFields()
                    popMessage(
                        resId = R.string.user_added_successfully_label,
                        color = R.color.viridian
                    )
                } else {
                    popMessage(
                        resId = R.string.user_with_email_already_exists_label,
                        color = R.color.amaranth
                    )
                }
            }
        }
    }

    private fun setListenerOnOpenUpdatePage() = binding.run {
        updateButton.setOnClickListener {
            if (userViewModel.isEmpty()) {
                root.popMessage(
                    resId = R.string.no_users_available_label,
                    color = R.color.amaranth
                )
                return@setOnClickListener
            }
            val randomUser = userViewModel.getRandomUser()

            // pass user as Parcelable
            val bundle = bundleOf(
                IntentKeys.EXTRA_USER to randomUser,
                IntentKeys.EXTRA_ACTIVE_COUNT to userViewModel.activeUsersCounter,
                IntentKeys.EXTRA_DELETED_COUNT to userViewModel.deletedUsersCounter
            )
            findNavController().navigate(R.id.action_mainFragment_to_updateFragment, bundle)
        }
    }

    private fun updateCounters() = binding.run {
        activeUsers.text =
            getString(R.string.active_users_label, userViewModel.activeUsersCounter)
        deletedUsers.text =
            getString(R.string.deleted_users_label, userViewModel.deletedUsersCounter)
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
            resId = R.string.all_fields_must_be_filled_in_label,
            color = R.color.amaranth
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
