package com.example.tbc_android_2025.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val firstName: String,
    val lastName: String,
    val age: Int,
    val email: String
) : Parcelable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User) return false
        return email.equals(other = other.email, ignoreCase = true)
    }

    override fun hashCode() = email.lowercase().hashCode()
}
