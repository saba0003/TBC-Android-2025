package com.example.tbc_android_2025.data.util

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
fun String.toLocalDateTime(): LocalDateTime =
    Instant.parse(input = this).toLocalDateTime(timeZone = TimeZone.currentSystemDefault())
