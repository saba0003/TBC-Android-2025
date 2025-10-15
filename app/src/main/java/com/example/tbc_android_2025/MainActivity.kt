package com.example.tbc_android_2025

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.tbc_android_2025.IntentKeys.EXTRA_ACTIVE_COUNT
import com.example.tbc_android_2025.IntentKeys.EXTRA_DELETED_COUNT
import com.example.tbc_android_2025.IntentKeys.EXTRA_USER
import com.example.tbc_android_2025.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val users: MutableSet<User> = mutableSetOf()

    private var activeUsersCounter = 0
    private var deletedUsersCounter = 0

    private val updateLauncher =
        registerForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode != RESULT_OK || result.data == null)
                return@registerForActivityResult

            val data = result.data!!
            val action =
                data.getStringExtra(IntentKeys.RESULT_ACTION) ?: return@registerForActivityResult
            val user = data.getParcelableExtra<User>(IntentKeys.EXTRA_USER)
                ?: return@registerForActivityResult

            when (action) {
                IntentKeys.ACTION_UPDATED -> {
                    val removed =
                        users.removeIf { it.email == user.email }
                    users.add(user)
                    val msgRes =
                        if (removed) R.string.user_updated_successfully_label else R.string.user_added_successfully_label
                    updateCounters()
                    binding.root.popMessage(msgRes, R.color.viridian)
                }

                IntentKeys.ACTION_REMOVED -> {
                    val removed = users.removeIf { it.email == user.email }
                    if (removed) {
                        deletedUsersCounter++
                        updateCounters()
                        binding.root.popMessage(
                            R.string.user_deleted_successfully_label,
                            R.color.viridian
                        )
                    } else {
                        binding.root.popMessage(
                            R.string.user_does_not_exist_label,
                            R.color.amaranth
                        )
                    }
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState = savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(view = binding.root)
        updateCounters()

        setListenerOnAddButton()
        setListenerOnOpenUpdatePage()
    }

    private fun setListenerOnAddButton() = binding.run {
        addButton.setOnClickListener {
            if (!allFieldsAreFilledIn()) return@setOnClickListener
            val user = getUserFromInput()
            if (!validateEmail(email = user.email)) return@setOnClickListener

            val added = users.add(user)
            if (added) {
                activeUsersCounter++
                updateCounters()
                addButton.popMessage(
                    resId = R.string.user_added_successfully_label,
                    color = R.color.viridian
                )
                clearFields()
            } else {
                addButton.popMessage(
                    resId = R.string.user_with_email_already_exists_label,
                    color = R.color.amaranth
                )
            }
        }
    }

    private fun setListenerOnOpenUpdatePage() = binding.run {
        updateButton.setOnClickListener {
            if (users.isEmpty()) {
                updateButton.popMessage(
                    resId = R.string.no_users_available_label,
                    color = R.color.amaranth
                )
                return@setOnClickListener
            }

            val randomUser = users.random()

            val intent = Intent(this@MainActivity, UpdateActivity::class.java).apply {
                putExtra(EXTRA_USER, randomUser)
                putExtra(EXTRA_ACTIVE_COUNT, activeUsersCounter)
                putExtra(EXTRA_DELETED_COUNT, deletedUsersCounter)
            }
            updateLauncher.launch(input = intent)
        }
    }


    private fun updateCounters() = binding.run {
        activeUsers.text = getString(R.string.active_users_label, activeUsersCounter)
        deletedUsers.text = getString(R.string.deleted_users_label, deletedUsersCounter)
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

    private fun validateEmail(email: String): Boolean = binding.run {
        when {
            email.isBlank() -> {
                emailEditText.popMessage(
                    resId = R.string.empty_email_input_label,
                    color = R.color.amaranth
                )
                false
            }

            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                emailEditText.popMessage(
                    resId = R.string.incorrect_email_input_format_label,
                    color = R.color.amaranth
                )
                false
            }

            else -> true
        }
    }
}
