package com.example.tbc_android_2025.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.tbc_android_2025.data.dtos.RemoteUserDto

object UserDiffCallback : DiffUtil.ItemCallback<RemoteUserDto>() {

    override fun areItemsTheSame(oldUser: RemoteUserDto, newUser: RemoteUserDto): Boolean =
        oldUser.id == newUser.id

    override fun areContentsTheSame(oldUser: RemoteUserDto, newUser: RemoteUserDto): Boolean =
        oldUser == newUser

}
