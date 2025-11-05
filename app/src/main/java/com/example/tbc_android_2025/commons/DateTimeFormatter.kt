package com.example.tbc_android_2025.commons


import com.example.tbc_android_2025.commons.UsefulStrings.TODAY
import com.example.tbc_android_2025.commons.UsefulStrings.YESTERDAY
import com.example.tbc_android_2025.commons.UsefulStrings.DATE_PATTERN_MONTH_DAY
import com.example.tbc_android_2025.commons.UsefulStrings.TIME_PATTERN_HOUR_MINUTE
import java.time.LocalDate
import java.time.LocalDateTime


object DateTimeFormatter {

    fun formatDateTime(dateTime: LocalDateTime): String {
        val now = LocalDate.now()
        val messageDate = dateTime.toLocalDate()

        val dayPart = when {
            messageDate.isEqual(now) -> TODAY
            messageDate.isEqual(now.minusDays(1)) -> YESTERDAY
            else -> dateTime.format(
                java.time.format.DateTimeFormatter.ofPattern(
                    DATE_PATTERN_MONTH_DAY
                )
            )
        }

        val timePart =
            dateTime.format(java.time.format.DateTimeFormatter.ofPattern(TIME_PATTERN_HOUR_MINUTE))
                .lowercase()

        return "$dayPart, $timePart"
    }
}
