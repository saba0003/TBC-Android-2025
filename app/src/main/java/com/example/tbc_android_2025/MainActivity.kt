package com.example.tbc_android_2025

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.tbc_android_2025.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val users = mutableListOf(
        User(
            id = 1,
            firstName = "გრიშა",
            lastName = "ონიანი",
            birthday = "1724647601641",
            address = "სტალინის სახლმუზეუმი",
            email = "grisha@mail.ru",
        ),
        User(
            id = 2,
            firstName = "Jemal",
            lastName = "Kakauridze",
            birthday = "1714647601641",
            address = "თბილისი, ლილოს მიტოვებული ქარხანა",
            email = "jemal@gmail.com",
        ),
        User(
            id = 3,
            firstName = "Omger",
            lastName = "Kakauridze",
            birthday = "1724647701641",
            address = "თბილის, ასათიანი 18",
            email = "omger@gmail.com",
        ),
        User(
            id = 32,
            firstName = "ბორის",
            lastName = "გარუჩავა",
            birthday = "1714947701641",
            address = "თბილისი, იაშვილი 14",
            email = "",
        ),
        User(
            id = 4,
            firstName = "ავთო",
            lastName = "სიხარულიძე",
            birthday = "1711947701641",
            address = "ფოთი",
            email = "tebzi@gmail.com",
        )
    )

    private val addUserLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val newUser = result.data?.getParcelableExtra<User>(AddUserActivity.EXTRA_NEW_USER)
            val previousQuery = result.data?.getStringExtra(AddUserActivity.EXTRA_PREVIOUS_QUERY)
            if (newUser != null) {
                users.add(newUser)
            }
            previousQuery?.let {
                binding.searchEditText.setText(it)
                binding.searchEditText.setSelection(it.length)
                performSearch(it)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addNewUserButton.setOnClickListener { launchAddUser(binding.searchEditText.text?.toString() ?: "") }

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val q = s?.toString() ?: ""
                performSearch(q)
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun performSearch(queryRaw: String) {
        val query = queryRaw.trim()
        binding.addNewUserButton.visibility = android.view.View.GONE
        binding.suggestionTextView.visibility = android.view.View.GONE
        if (query.isEmpty())
            return

        val qLower = query.lowercase(Locale.getDefault())

        val found = users.firstOrNull { user ->
            val birthdayDisplay = user.birthdayDisplay().lowercase(Locale.getDefault())
            listOf(
                user.firstName.lowercase(Locale.getDefault()),
                user.lastName.lowercase(Locale.getDefault()),
                birthdayDisplay,
                user.address.lowercase(Locale.getDefault()),
                user.email.lowercase(Locale.getDefault())
            ).any { it.contains(qLower) }
        }

        if (found != null) {
            val display = "${found.firstName} ${found.lastName} • ${found.birthdayDisplay()}"
            binding.suggestionTextView.text = display
            binding.suggestionTextView.visibility = android.view.View.VISIBLE
            binding.addNewUserButton.visibility = android.view.View.GONE
        } else {
            binding.suggestionTextView.visibility = android.view.View.GONE
            binding.addNewUserButton.visibility = android.view.View.VISIBLE

            Snackbar.make(binding.root, "User not found", Snackbar.LENGTH_INDEFINITE)
                .setAction("Add new User") { launchAddUser(query) }
                .show()
        }
    }

    private fun launchAddUser(previousQuery: String) {
        val intent = Intent(this, AddUserActivity::class.java)
        intent.putExtra(AddUserActivity.EXTRA_PREVIOUS_QUERY, previousQuery)
        addUserLauncher.launch(intent)
    }
}
