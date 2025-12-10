package com.example.tbc_android_2025.presentation.mappers

import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
fun Long.format(): String {
    val instant = Instant.fromEpochMilliseconds(epochMilliseconds = this)
    val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
    val month = localDateTime.month.name.lowercase().replaceFirstChar {
        if (it.isLetter()) it.titlecase() else it.toString()
    }
    val dayOfMonth = localDateTime.day
    val time = localDateTime.time
    val hour12 = when (time.hour) {
        0 -> 12
        in 1..12 -> time.hour
        else -> time.hour - 12
    }
    val amPm = if (time.hour < 12) "AM" else "PM"
    val minute = time.minute.toString().padStart(length = 2, padChar = '0')
    return "$dayOfMonth $month at $hour12:$minute $amPm"
}
