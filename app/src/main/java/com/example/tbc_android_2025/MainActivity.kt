package com.example.tbc_android_2025

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tbc_android_2025.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val words = mutableSetOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            words.add(word)
            binding.anagramInput.text.clear()
            Toast.makeText(this, "Saved: $word", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Please enter a word dumbass", Toast.LENGTH_SHORT).show()
        }
    }

    /** Not case-sensitive */
    private fun showAnagramGroups() {
        val grouped = words.groupBy { it.lowercase().toCharArray().sorted().joinToString("") }
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
        words.clear()
        binding.anagramInput.text.clear()
        binding.anagramOutput.text = getString(R.string.anagrams_label)
        Toast.makeText(this, "Cleared!", Toast.LENGTH_SHORT).show()
    }
}
