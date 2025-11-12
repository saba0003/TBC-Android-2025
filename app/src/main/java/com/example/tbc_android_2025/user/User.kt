package com.example.tbc_android_2025.user

data class User(
    val id: Int = nextId(),
    val email: String,
    val username: String,
    val password: String
) {
    companion object {
        var counter: Int = 0

        fun nextId(): Int = ++counter
    }
}
