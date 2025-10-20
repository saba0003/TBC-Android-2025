package com.example.tbc_android_2025.models

import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {

    private val _users = mutableSetOf<User>()

    val users: Set<User> get() = _users

    var activeUsersCounter = 0
        private set
    var deletedUsersCounter = 0
        private set

    fun addUser(user: User): Boolean {
        val exists = _users.any { it.email.equals(other = user.email, ignoreCase = true) }
        if (exists) return false
        _users.add(element = user)
        activeUsersCounter++
        return true
    }

    fun updateUser(oldEmail: String, newUser: User): Boolean {
        val removed = _users.removeIf { it.email.equals(other = oldEmail, ignoreCase = true) }
        _users.add(element = newUser)
        return removed
    }

    fun removeUser(email: String): Boolean {
        val removed = _users.removeIf { it.email.equals(other = email, ignoreCase = true) }
        if (removed) deletedUsersCounter++
        return removed
    }

    fun isEmpty() = _users.isEmpty()

    fun getRandomUser(): User = _users.random()
}
