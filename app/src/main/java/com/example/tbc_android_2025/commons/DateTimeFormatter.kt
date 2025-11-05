package com.example.tbc_android_2025.commons

import java.time.LocalDate
import java.time.LocalDateTime

object DateTimeFormatter {

    fun formatDateTime(dateTime: LocalDateTime): String {
        val now = LocalDate.now()
        val messageDate = dateTime.toLocalDate()

        val dayPart = when {
            messageDate.isEqual(now) -> "Today"
            messageDate.isEqual(now.minusDays(1)) -> "Yesterday"
            else -> dateTime.format(java.time.format.DateTimeFormatter.ofPattern("MMM d"))
        }

        val timePart = dateTime.format(java.time.format.DateTimeFormatter.ofPattern("h:mma")).lowercase()

        return "$dayPart, $timePart"
    }
}
