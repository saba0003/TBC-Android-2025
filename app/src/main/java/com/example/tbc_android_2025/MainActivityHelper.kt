//package com.example.tbc_android_2025
//
//import android.util.Patterns
//import android.view.View
//import com.example.tbc_android_2025.MainActivityHelper.UserFormUtils.addUser
//import com.example.tbc_android_2025.MainActivityHelper.UserFormUtils.updateUser
//import com.example.tbc_android_2025.databinding.ActivityMainBinding
//
//object MainActivityHelper {
//
//    private var users = mutableSetOf<User>()
//    private var activeUsersCounter = 0
//    private var deletedUsersCounter = 0
//    private var userToUpdate: User? = null
//
//    fun setListenerOnAddButton(binding: ActivityMainBinding) = with(receiver = binding) {
//        addButton.setOnClickListener {
//            if (!allFieldsAreFilledIn(binding = this))
//                return@setOnClickListener
//
//            val user = getUserFromInput(binding = this)
//
//            if (!validateEmail(binding = this, email = user.email))
//                return@setOnClickListener
//
//            addUser(
//                binding = binding,
//                users = users,
//                user = user
//            )
//        }
//    }
//
//    fun setListenerOnUpdateButton(binding: ActivityMainBinding) = with(receiver = binding) {
//        updateButton.setOnClickListener {
//            val email = emailEditText.text.toString().trim()
//
//            if (userToUpdate == null) {
//                if (!validateEmail(binding = this, email = email)) {
//                    updateButton.popMessage(
//                        resId = R.string.enter_email_to_search_label,
//                        color = R.color.amaranth
//                    )
//                    return@setOnClickListener
//                }
//
//                val (messageResId, messageColor) = run {
//                    users.find { it.email == email }?.let {
//                        userToUpdate = it
//                        clearFields(binding = this)
//                        R.string.user_found_update_fields_label to R.color.viridian
//                    } ?: (R.string.user_does_not_exist_label to R.color.amaranth)
//                }
//
//                updateButton.popMessage(resId = messageResId, color = messageColor)
//
//                return@setOnClickListener
//            }
//
//            if (!allFieldsAreFilledIn(binding = this))
//                return@setOnClickListener
//
//            getUserFromInput(binding = this).also {
//                updateUser(
//                    binding = binding,
//                    users = users,
//                    oldUser = userToUpdate!!,
//                    newUser = it
//                )
//            }
//        }
//    }
//
//    fun setListenerOnRemoveButton(binding: ActivityMainBinding) = with(receiver = binding) {
//        removeButton.setOnClickListener {
//            val email = emailEditText.text.toString().trim()
//            if (!validateEmail(binding = this, email = email))
//                return@setOnClickListener
//
//            val (messageResId, messageColor) = run {
//                if (users.removeIf { it.email == email }) {
//                    clearFields(binding = this)
//                    updateCounters(
//                        binding = this,
//                        counter = CounterType.DELETED,
//                        increment = true
//                    )
//                    R.string.user_deleted_successfully_label to R.color.viridian
//                } else {
//                    R.string.user_does_not_exist_label to R.color.amaranth
//                }
//            }
//
//            removeButton.popMessage(resId = messageResId, color = messageColor)
//        }
//    }
//
//    fun allFieldsAreFilledIn(binding: ActivityMainBinding): Boolean = with(receiver = binding) {
//        val fields = listOf(
//            firstNameEditText,
//            lastNameEditText,
//            ageEditText,
//            emailEditText
//        )
//        val allFilled = fields.none { it.text.isNullOrBlank() }
//        if (!allFilled)
//            root.popMessage(
//                resId = R.string.all_fields_must_be_filled_in_label,
//                color = R.color.amaranth
//            )
//        return allFilled
//    }
//
//    fun validateEmail(binding: ActivityMainBinding, email: String): Boolean =
//        with(receiver = binding) {
//            when {
//                email.isBlank() -> {
//                    emailEditText.popMessage(
//                        resId = R.string.empty_email_input_label,
//                        color = R.color.amaranth
//                    )
//                    false
//                }
//
//                !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
//                    emailEditText.popMessage(
//                        resId = R.string.incorrect_email_input_format_label,
//                        color = R.color.amaranth
//                    )
//                    false
//                }
//
//                else -> true
//            }
//        }
//
//    fun clearFields(binding: ActivityMainBinding) {
//        binding.apply {
//            firstNameEditText.text?.clear()
//            lastNameEditText.text?.clear()
//            ageEditText.text?.clear()
//            emailEditText.text?.clear()
//        }
//    }
//
//    fun getUserFromInput(binding: ActivityMainBinding): User = with(receiver = binding) {
//        User(
//            firstName = firstNameEditText.text.toString().trim(),
//            lastName = lastNameEditText.text.toString().trim(),
//            age = ageEditText.text.toString().trim().toInt(),
//            email = emailEditText.text.toString().trim()
//        )
//    }
//
//    fun updateCounters(
//        binding: ActivityMainBinding,
//        counter: CounterType? = null,
//        increment: Boolean = false
//    ) = with(receiver = binding) {
//        when (counter) {
//            CounterType.ACTIVE -> if (increment) activeUsersCounter++
//            CounterType.DELETED -> if (increment) deletedUsersCounter++
//            null -> {}
//        }
//        activeUsers.text = root.context.getString(R.string.active_users_label, activeUsersCounter)
//        deletedUsers.text = root.context.getString(R.string.deleted_users_label, deletedUsersCounter)
//    }
//
//    object UserFormUtils {
//
//        fun addUser(
//            binding: ActivityMainBinding,
//            users: MutableSet<User>,
//            user: User
//        ) {
//            saveUser(
//                binding = binding,
//                user = user,
//                operation = { users.add(it) },
//                button = binding.addButton,
//                messageResId = R.string.user_added_successfully_label,
//                increment = true
//            )
//        }
//
//        fun updateUser(
//            binding: ActivityMainBinding,
//            users: MutableSet<User>,
//            oldUser: User,
//            newUser: User
//        ) {
//            saveUser(
//                binding = binding,
//                user = newUser,
//                operation = {
//                    users.remove(element = oldUser)
//                    users.add(element = it)
//                },
//                button = binding.updateButton,
//                messageResId = R.string.user_updated_successfully_label
//            )
//        }
//
//        private fun saveUser(
//            binding: ActivityMainBinding,
//            user: User,
//            operation: (User) -> Unit,
//            button: View,
//            messageResId: Int,
//            increment: Boolean = false
//        ) {
//            operation(user)
//            clearFields(binding)
//            button.popMessage(resId = messageResId, color = R.color.viridian)
//            if (increment)
//                updateCounters(
//                    binding = binding,
//                    counter = CounterType.ACTIVE,
//                    increment = true
//                )
//        }
//    }
//}
