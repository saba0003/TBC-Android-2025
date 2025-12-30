package com.example.tbc_android_2025.data.util

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
fun String.toUuid(): Uuid = Uuid.parse(uuidString = this)
