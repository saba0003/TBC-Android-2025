package com.example.tbc_android_2025.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.tbc_android_2025.data.models.User

object UserDiffCallback : DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldUser: User, newUser: User): Boolean = oldUser.id == newUser.id

    override fun areContentsTheSame(oldUser: User, newUser: User): Boolean = oldUser == newUser

}
