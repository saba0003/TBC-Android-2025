package com.example.tbc_android_2025.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Int = nextId(),
    val email: String,
    val password: String
) : Parcelable {
    companion object {

        var counter: Int = 0

        fun nextId(): Int = ++counter
    }
}
