package com.example.tbc_android_2025

import com.example.tbc_android_2025.MathUtils.Companion.calculateGCD
import com.example.tbc_android_2025.MathUtils.Companion.calculateLCM
import com.example.tbc_android_2025.MathUtils.Companion.containsSpecialCharacter
import com.example.tbc_android_2025.MathUtils.Companion.isPalindrome
import com.example.tbc_android_2025.MathUtils.Companion.numberReverse
import com.example.tbc_android_2025.MathUtils.Companion.sumUpNaturalEvensInRangeOf100BruteForceApproach
import com.example.tbc_android_2025.MathUtils.Companion.sumUpNaturalEvensInRangeOf100RecursiveApproach

fun main() {
    val testString = "Mr. Krabs loves $$$"
    val testPalindrome = "Radar"
    val testNumberToBeReversed = 10220
    println("GCD of 36 and 60: ${calculateGCD(a = 36, b = 60)}")
    println("LCM of 36 and 60: ${calculateLCM(a = 36, b = 60)}")
    println("String \"$testString\" contains symbol '$': ${containsSpecialCharacter(s = testString)}")
    println("Sum of evens (brute force): ${sumUpNaturalEvensInRangeOf100BruteForceApproach()}")
    println("Sum of evens (recursive): ${sumUpNaturalEvensInRangeOf100RecursiveApproach()}")
    println("The number - $testNumberToBeReversed - is reverse of - ${numberReverse(n = testNumberToBeReversed)}")
    println("The following string - \"$testPalindrome\" - is ${if (isPalindrome(s = testPalindrome)) "" else "not "}palindrome")
}
