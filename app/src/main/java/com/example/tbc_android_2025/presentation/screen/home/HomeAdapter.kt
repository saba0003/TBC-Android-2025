package com.example.tbc_android_2025.presentation.screen.home

import com.example.tbc_android_2025.databinding.ItemUserBinding as Binding
import com.example.tbc_android_2025.presentation.common.Strings
import com.example.tbc_android_2025.presentation.common.adapter.BasePagingAdapter
import com.example.tbc_android_2025.presentation.extension.loadImage
import com.example.tbc_android_2025.presentation.model.UserModel

class HomeAdapter : BasePagingAdapter<UserModel, Binding>(inflater = Binding::inflate) {

    override fun bind(binding: Binding, item: UserModel): Unit = with(receiver = binding) {
        userIdTextView.text = userIdTextView.context.getString(Strings.user_id, item.id)
        emailTextView.text = item.email
        firstNameTextView.text = item.firstName
        lastNameTextView.text = item.lastName
        avatarImageView.loadImage(url = item.avatar)
    }

}
