package com.example.tbc_android_2025

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    private val base = mapOf(
        1 to "ერთი", 2 to "ორი", 3 to "სამი", 4 to "ოთხი", 5 to "ხუთი", 6 to "ექვსი", 7 to "შვიდი",
        8 to "რვა", 9 to "ცხრა", 10 to "ათი", 11 to "თერთმეტი", 12 to "თორმეტი", 13 to "ცამეტი",
        14 to "თოთხმეტი", 15 to "თხუთმეტი", 16 to "თექვსმეტი", 17 to "ჩვიდმეტი", 18 to "თვრამეტი",
        19 to "ცხრამეტი", 20 to "ოცი", 40 to "ორმოცი", 60 to "სამოცი", 80 to "ოთხმოცი", 100 to "ასი",
        200 to "ორასი", 300 to "სამასი", 400 to "ოთხასი", 500 to "ხუთასი", 600 to "ექვსასი",
        700 to "შვიდასი", 800 to "რვაასი", 900 to "ცხრაასი", 1000 to "ათასი"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState = savedInstanceState)
        setContentView(layoutResID = R.layout.activity_main)

        val numberInput: EditText = findViewById(R.id.numberInput)
        val translateButton: Button = findViewById(R.id.translateButton)
        val translatedText: TextView = findViewById(R.id.translatedText)
        val languageToggle: Switch = findViewById(R.id.languageToggle)

        translateButton.setOnClickListener {
            val input: String = numberInput.text.toString()
            if (input.isNotBlank()) {
                val inputToNumber: Int = input.toInt()
                if (inputToNumber in 1..1000) {
                    val translation: String = if (languageToggle.isChecked)
                        translateNumberToEnglish(number = inputToNumber)
                    else
                        translateNumberToGeorgian(number = inputToNumber)
                    translatedText.text = translation
                } else {
                    translatedText.text = getString(R.string.invalid_input)
                }
            } else {
                translatedText.text = getString(R.string.translated_text)
            }
        }
    }

    private fun translateNumberToGeorgian(number: Int): String {
        return when {
            // simple cases
            base.containsKey(key = number) -> base[number]!!

            // 20s, 40s, 60s, 80s
            number in 21..39 -> "ოცდა" + base[number - 20]
            number in 41..59 -> "ორმოცდა" + base[number - 40]
            number in 61..79 -> "სამოცდა" + base[number - 60]
            number in 81..99 -> "ოთხმოცდა" + base[number - 80]

            // Hundreds
            number in 101..199 -> "ას " + translateNumberToGeorgian(number = number - 100)
            number in 201..299 -> "ორას " + translateNumberToGeorgian(number = number - 200)
            number in 301..399 -> "სამას " + translateNumberToGeorgian(number = number - 300)
            number in 401..499 -> "ოთხას " + translateNumberToGeorgian(number = number - 400)
            number in 501..599 -> "ხუთას " + translateNumberToGeorgian(number = number - 500)
            number in 601..699 -> "ექვსას " + translateNumberToGeorgian(number = number - 600)
            number in 701..799 -> "შვიდას " + translateNumberToGeorgian(number = number - 700)
            number in 801..899 -> "რვაას " + translateNumberToGeorgian(number = number - 800)
            number in 901..999 -> "ცხრაას " + translateNumberToGeorgian(number = number - 900)

            else -> throw IllegalArgumentException("აპლიკაცია ამ კონკრეტული რიცხვისთვის არ მუშაობს!")
        }
    }

    private fun translateNumberToEnglish(number: Int): String {
        val base = mapOf(
            1 to "one", 2 to "two", 3 to "three", 4 to "four", 5 to "five",
            6 to "six", 7 to "seven", 8 to "eight", 9 to "nine", 10 to "ten",
            11 to "eleven", 12 to "twelve", 13 to "thirteen", 14 to "fourteen",
            15 to "fifteen", 16 to "sixteen", 17 to "seventeen", 18 to "eighteen",
            19 to "nineteen", 20 to "twenty", 30 to "thirty", 40 to "forty",
            50 to "fifty", 60 to "sixty", 70 to "seventy", 80 to "eighty",
            90 to "ninety", 100 to "hundred", 1000 to "thousand"
        )

        return when {
            base.containsKey(key = number) -> base[number]!!

            number in 21..99 -> {
                val tens = (number / 10) * 10
                val units = number % 10
                base[tens]!! + if (units > 0) "-${base[units]}" else ""
            }

            number in 101..999 -> {
                val hundreds = number / 100
                val remainder = number % 100
                val hundredsPart = base[hundreds]!! + " hundred"
                if (remainder == 0)
                    hundredsPart
                else
                    hundredsPart + " and " + translateNumberToEnglish(remainder)
            }

            else -> throw IllegalArgumentException(getString(R.string.invalid_input))
        }
    }
}
