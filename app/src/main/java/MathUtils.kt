package com.example.tbc_android_2025

class MathUtils {

    companion object {

        fun calculateGCD(a: Int, b: Int): Int {
            var num1 = a
            var num2 = b

            while (num2 != 0) {
                val temp = num2
                num2 = num1 % num2
                num1 = temp
            }

            return num1
        }

        fun calculateLCM(a: Int, b: Int): Int = (a * b) / calculateGCD(a = a, b = b)

        fun containsSpecialCharacter(s: String, symbol: Char = '$'): Boolean {
            for (char in s)
                if (char == symbol)
                    return true
            return false
        }

        fun sumUpNaturalEvensInRangeOf100BruteForceApproach(): Int {
            var sum = 0
            for (i in 2..98 step 2)
                sum += i
            return sum
        }

        fun sumUpNaturalEvensInRangeOf100RecursiveApproach(n: Int = 0): Int {
            if (n > 99) return 0
            return n + sumUpNaturalEvensInRangeOf100RecursiveApproach(n = n + 2)
        }

        fun numberReverse(n: Int): Int { // e.g. 10220 -> 2201
            var original = n
            var reversed = 0
            var remainder: Int

            while (original > 0) {
                remainder = original % 10
                reversed = reversed * 10 + remainder
                original /= 10
            }

            return reversed
        }

        /** Not case-sensitive */
        fun isPalindrome(s: String): Boolean {
            val reversed = s.reversed().lowercase()
            return s.lowercase() == reversed
        }
    }
}
