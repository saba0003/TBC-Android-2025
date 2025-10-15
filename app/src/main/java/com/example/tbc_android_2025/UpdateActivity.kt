package com.example.tbc_android_2025

import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.example.tbc_android_2025.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var originalUser: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState = savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(view = binding.root)

        val active = intent.getIntExtra(IntentKeys.EXTRA_ACTIVE_COUNT, 0)
        val deleted = intent.getIntExtra(IntentKeys.EXTRA_DELETED_COUNT, 0)

        originalUser = intent.getParcelableExtra(IntentKeys.EXTRA_USER) ?: run { finish(); return }
        bindUserToFields(originalUser)

        updateCounters(active = active, deleted = deleted)

        originalUser = intent.getParcelableExtra(IntentKeys.EXTRA_USER) ?: run {
            finish()
            return
        }

        bindUserToFields(originalUser)

        binding.backButton.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }

        binding.updateButton.setOnClickListener {
            if (!allFieldsAreFilledIn()) return@setOnClickListener

            val updatedUser = getUserFromInput()
            if (!validateEmail(email = updatedUser.email)) return@setOnClickListener

            val data = intent.apply {
                putExtra(IntentKeys.EXTRA_USER, updatedUser)
                putExtra(IntentKeys.RESULT_ACTION, IntentKeys.ACTION_UPDATED)
            }
            setResult(RESULT_OK, data)
            finish()
        }

        binding.removeButton.setOnClickListener {
            val data = intent.apply {
                putExtra(IntentKeys.EXTRA_USER, originalUser)
                putExtra(IntentKeys.RESULT_ACTION, IntentKeys.ACTION_REMOVED)
            }
            setResult(RESULT_OK, data)
            finish()
        }
    }

    private fun bindUserToFields(user: User) {
        binding.apply {
            firstNameEditText.setText(user.firstName)
            lastNameEditText.setText(user.lastName)
            ageEditText.setText(user.age.toString())
            emailEditText.setText(user.email)
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

    private fun validateEmail(email: String): Boolean = binding.run {
        return when {
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

    private fun updateCounters(active: Int, deleted: Int) = binding.run {
        activeUsers.text = getString(R.string.active_users_label, active)
        deletedUsers.text = getString(R.string.deleted_users_label, deleted)
    }
}
