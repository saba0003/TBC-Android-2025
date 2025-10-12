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

        with(receiver = binding) {
            addButton.setOnClickListener {
                if (!allFieldsAreFilledIn())
                    return@setOnClickListener

                val user = getUserFromInput()

                if (!validateEmail(email = user.email))
                    return@setOnClickListener

                addUser(user = user)
            }

            updateButton.setOnClickListener {
                val email = emailEditText.text.toString().trim()

                if (userToUpdate == null) {
                    if (!validateEmail(email = email)) {
                        updateButton.popMessage(resId = R.string.enter_email_to_search_label)
                        return@setOnClickListener
                    }

                    users.find { it.email == email }?.let {
                        userToUpdate = it
                        clearFields()
                        updateButton.popMessage(resId = R.string.user_found_update_fields_label)
                    } ?: updateButton.popMessage(resId = R.string.user_does_not_exist_label)

                    return@setOnClickListener
                }

                if (!allFieldsAreFilledIn())
                    return@setOnClickListener

                getUserFromInput().also { updatedUser ->
                    updateUser(user = updatedUser)
                }
            }

            removeButton.setOnClickListener {
                val email = emailEditText.text.toString().trim()
                if (!validateEmail(email = email))
                    return@setOnClickListener

                val messageResId = if (users.removeIf { it.email == email }) {
                    clearFields()
                    updateCounters(counter = CounterType.DELETED, increment = true)
                    R.string.user_deleted_successfully_label
                } else {
                    R.string.user_does_not_exist_label
                }

                removeButton.popMessage(resId = messageResId)
            }
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

    private fun validateEmail(email: String): Boolean = with(receiver = binding.emailEditText) {
        return when {
            email.isBlank() -> {
                popMessage(resId = R.string.empty_email_input_label)
                false
            }

            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                popMessage(resId = R.string.incorrect_email_input_format_label)
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
            root.popMessage(resId = R.string.all_fields_must_be_filled_in_label)
        allFilled
    }

    private fun getUserFromInput(): User = with(receiver = binding) {
        User(
            firstName = firstNameEditText.text.toString().trim(),
            lastName = lastNameEditText.text.toString().trim(),
            age = ageEditText.text.toString().trim().toInt(),
            email = emailEditText.text.toString().trim()
        )
    }

    private fun saveUser(
        user: User,
        operation: (User) -> Unit,
        button: View,
        messageRes: Int,
        updateCounter: Boolean = false
    ) {
        operation(user)
        clearFields()
        button.popMessage(resId = messageRes)
        if (updateCounter)
            updateCounters(counter = CounterType.ACTIVE, increment = true)
    }


    private fun addUser(user: User) {
        saveUser(
            user = user,
            operation = { users.add(it) },
            button = binding.addButton,
            messageRes = R.string.user_added_successfully_label,
            updateCounter = true
        )
    }

    private fun updateUser(user: User) {
        saveUser(
            user = user,
            operation = {
                users.remove(element = userToUpdate)
                users.add(element = it)
                userToUpdate = null
            },
            button = binding.updateButton,
            messageRes = R.string.user_updated_successfully_label
        )
    }
}
