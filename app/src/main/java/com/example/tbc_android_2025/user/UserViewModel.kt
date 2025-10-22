package com.example.tbc_android_2025.user

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserViewModel : ViewModel() {

    private val _users = mutableSetOf(
        User(firstName = "Alex", lastName = "Mercer", age = 29, email = "mercer_alex@heavensgate.afterlife"),
        User(firstName = "James", lastName = "Heller", age = 36, email = "heller_james@hellgate.afterlife"),
        User(firstName = "Lewis", lastName = "Hamilton", age = 40, email = "hamilton_lewis@f1.racing"),
        User(firstName = "Jann", lastName = "Mardenborough", age = 34, email = "mardenborough_jann@gt.racing"),
        User(firstName = "Dante", lastName = "Spardason", age = 44, email = "spardason_dante@devilhunter.deviltrigger"),
        User(firstName = "Vergil", lastName = "Spardason", age = 43, email = "spardason_vergil@alphaandomega.deviltrigger"),
        User(firstName = "Alex", lastName = "Mason", age = 35, email = "mason_alex@numbers.sog"),
    )

    // reactive view for UI
    private val _usersFlow = MutableStateFlow<List<User>>(value = emptyList())

    val usersFlow: StateFlow<List<User>> get() = _usersFlow

    var activeUsersCounter = 0
        private set
    var deletedUsersCounter = 0
        private set

    init {
        activeUsersCounter = _users.size
        emit()
    }

    fun addUser(user: User): Boolean {
        val exists: Boolean = _users.any { it.email.equals(other = user.email, ignoreCase = true) }
        if (exists)
            return false
        _users.add(element = user)
        activeUsersCounter++
        emit()
        return true
    }

    fun updateUser(oldEmail: String, newUser: User): Boolean {
        val removed: Boolean = _users.removeIf { it.email.equals(other = oldEmail, ignoreCase = true) }
        _users.add(element = newUser)
        emit()
        return removed
    }

    fun removeUser(email: String): Boolean {
        val removed: Boolean = _users.removeIf { it.email.equals(other = email, ignoreCase = true) }
        if (removed) {
            activeUsersCounter--
            deletedUsersCounter++
            emit()
        }
        return removed
    }

    fun isEmpty(): Boolean = _users.isEmpty()

    fun getRandomUser(): User = _users.random()

    private fun emit() {
        // deterministic order for UI — convert to list
        _usersFlow.value = _users.toList()
    }
}
