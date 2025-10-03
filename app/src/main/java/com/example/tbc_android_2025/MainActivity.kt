package com.example.tbc_android_2025

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tbc_android_2025.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val anagrams = mutableSetOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.saveButton.setOnClickListener { save() }
        binding.outputButton.setOnClickListener { showAnagramGroups() }
        binding.clearButton.setOnClickListener { clear() }
    }

    /** Not case-sensitive */
    private fun save() {
        val word = binding.anagramInput.text.toString().trim()
        if (word.isNotEmpty()) {
            anagrams.add(word)
            binding.anagramInput.text.clear()
            Toast.makeText(this, getString(R.string.save_button_message, word), Toast.LENGTH_SHORT)
                .show()
        } else {
            Toast.makeText(this, R.string.empty_input_exception_message, Toast.LENGTH_SHORT).show()
        }
    }

    /** Not case-sensitive */
    private fun showAnagramGroups() {
        val grouped: Map<String, List<String>> =
            anagrams.groupBy { it.lowercase().toCharArray().sorted().joinToString("") }
        val builder = StringBuilder()

        grouped.values.forEachIndexed { index, group ->
            builder.append("Group ${index + 1}: ")
            builder.append(group.joinToString(", "))
            builder.append("\n\n")
        }

        builder.append("\nNumber of anagram groups: ${grouped.size}")

        binding.anagramOutput.text = builder.toString()
    }

    private fun clear() {
        anagrams.clear()
        binding.anagramInput.text.clear()
        binding.anagramOutput.text = getString(R.string.anagrams_label)
        Toast.makeText(this, R.string.clear_button_message, Toast.LENGTH_SHORT).show()
    }
}
