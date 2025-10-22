package com.example.tbc_android_2025.user

import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {

    private val users = mutableSetOf(
        User(
            firstName = "Alex",
            lastName = "Mercer",
            age = 29,
            email = "mercer_alex@heavensgate.afterlife"
        ),
        User(
            firstName = "James",
            lastName = "Heller",
            age = 36,
            email = "heller_james@hellgate.afterlife"
        ),
        User(
            firstName = "Lewis",
            lastName = "Hamilton",
            age = 40,
            email = "hamilton_lewis@f1.racing"
        ),
        User(
            firstName = "Jann",
            lastName = "Mardenborough",
            age = 34,
            email = "mardenborough_jann@gt.racing"
        ),
        User(
            firstName = "Dante",
            lastName = "Spardason",
            age = 44,
            email = "spardason_dante@devilhunter.deviltrigger"
        ),
        User(
            firstName = "Vergil",
            lastName = "Spardason",
            age = 43,
            email = "spardason_vergil@alphaandomega.deviltrigger"
        ),
        User(
            firstName = "Alex",
            lastName = "Mason",
            age = 35,
            email = "mason_alex@numbers.sog"
        )
    )

    var activeUsersCounter = getUsersCount()
    var deletedUsersCounter = 0

    fun getUsers(): List<User> = users.toList()

    fun addUser(user: User): Boolean {
        if (users.any { it.email.equals(other = user.email, ignoreCase = true) })
            return false
        users.add(element = user)
        activeUsersCounter++
        return true
    }

    fun updateUser(oldEmail: String, newUser: User): Boolean {
        val removed = users.removeIf { it.email.equals(other = oldEmail, ignoreCase = true) }
        users.add(element = newUser)
        return removed
    }

    fun removeUser(email: String): Boolean {
        val removed = users.removeIf { it.email.equals(other = email, ignoreCase = true) }
        if (removed) {
            activeUsersCounter--
            deletedUsersCounter++
        }
        return removed
    }

    fun isEmpty(): Boolean = users.isEmpty()

    fun getUsersCount(): Int = users.size

    fun getRandomUser(): User = users.random()
}
