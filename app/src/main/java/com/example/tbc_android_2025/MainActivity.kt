package com.example.tbc_android_2025

import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.tbc_android_2025.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var users: MutableSet<User>
    private var activeUsersCounter = 0
    private var deletedUsersCounter = 0
    private var userToUpdate: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState = savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        users = mutableSetOf()

        updateCounters()

        setContentView(binding.root)

        binding.firstNameEditText.setText("saba")
        binding.lastNameEditText.setText("sm")
        binding.ageEditText.setText("20")
        binding.emailEditText.setText("saba@gmail.com")

        binding.addButton.setOnClickListener {
            if (allFieldsAreFilledIn()) {
                val firstName = binding.firstNameEditText.text.toString().trim()
                val lastName = binding.lastNameEditText.text.toString().trim()
                val age = binding.ageEditText.text.toString().trim().toInt()
                val email = binding.emailEditText.text.toString().trim()

                if (validateEmail(view = binding.emailEditText, email = email)) {
                    users.add(
                        User(
                            firstName = firstName,
                            lastName = lastName,
                            age = age,
                            email = email
                        )
                    )
                    binding.addButton.popMessage(resId = R.string.user_added_successfully_label)
                    clearFields()
                    updateCounters(counter = CounterType.ACTIVE, increment = true)
                }
            }
        }

        binding.removeButton.setOnClickListener {
            val email = binding.emailEditText.text.toString().trim()
            if (validateEmail(view = binding.emailEditText, email = email)) {
                val removed = users.removeIf { it.email == email }
                if (removed) {
                    clearFields()
                    binding.removeButton.popMessage(resId = R.string.user_deleted_successfully_label)
                    updateCounters(counter = CounterType.DELETED, increment = true)
                } else {
                    binding.removeButton.popMessage(resId = R.string.user_does_not_exist_label)
                }
            }
        }

        binding.updateButton.setOnClickListener {
            val email = binding.emailEditText.text.toString().trim()
            val firstName = binding.firstNameEditText.text.toString().trim()
            val lastName = binding.lastNameEditText.text.toString().trim()
            val age = binding.ageEditText.text.toString().trim().toIntOrNull()

            if (userToUpdate == null) {
                if (!validateEmail(view = binding.emailEditText, email = email)) {
                    binding.updateButton.popMessage(resId = R.string.enter_email_to_search_label)
                    return@setOnClickListener
                }

                val existingUser = users.find { it.email == email }

                if (existingUser != null) {
                    userToUpdate = existingUser
                    binding.root.popMessage(resId = R.string.user_found_update_fields_label)
                    clearFields()
                } else {
                    binding.root.popMessage(resId = R.string.user_does_not_exist_label)
                }

                return@setOnClickListener
            }

            if (!allFieldsAreFilledIn())
                return@setOnClickListener

            val updatedUser = User(
                firstName = firstName,
                lastName = lastName,
                age = age!!,
                email = email
            )

            users.remove(element = userToUpdate)
            users.add(element = updatedUser)

            binding.updateButton.popMessage(resId = R.string.user_updated_successfully_label)
            clearFields()
            userToUpdate = null
        }
    }

    private fun updateCounters(counter: CounterType? = null, increment: Boolean = false) =
        with(receiver = binding) {
            when (counter) {
                CounterType.ACTIVE -> {
                    if (increment) activeUsersCounter++
                    activeUsers.text = getString(R.string.active_users_label, activeUsersCounter)
                }

                CounterType.DELETED -> {
                    if (increment) deletedUsersCounter++
                    deletedUsers.text = getString(R.string.deleted_users_label, deletedUsersCounter)
                }

                null -> {
                    activeUsers.text = getString(R.string.active_users_label, activeUsersCounter)
                    deletedUsers.text = getString(R.string.deleted_users_label, deletedUsersCounter)
                }
            }
        }

    private fun validateEmail(view: View, email: String): Boolean {
        return when {
            email.isBlank() -> {
                view.popMessage(resId = R.string.empty_email_input_label)
                false
            }

            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                view.popMessage(resId = R.string.incorrect_email_input_format_label)
                false
            }

            else -> true
        }
    }

    private fun clearFields() = with(receiver = binding) {
        listOf(
            firstNameEditText,
            lastNameEditText,
            ageEditText,
            emailEditText
        ).forEach { it.text?.clear() }
    }

    private fun allFieldsAreFilledIn(): Boolean = with(receiver = binding) {
        val fields = listOf(firstNameEditText, lastNameEditText, ageEditText, emailEditText)
        val allFilled = fields.none { it.text.isNullOrBlank() }
        if (!allFilled)
            root.popMessage(R.string.all_fields_must_be_filled_in_label)
        allFilled
    }
}
