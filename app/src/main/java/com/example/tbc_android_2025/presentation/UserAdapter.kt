package com.example.tbc_android_2025.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tbc_android_2025.data.models.User
import com.example.tbc_android_2025.databinding.ItemUserBinding as Binding

typealias UserListAdapter = ListAdapter<User, UserAdapter.UserViewHolder>

class UserAdapter : UserListAdapter(UserDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, ignored: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = Binding.inflate(inflater, parent, false)
        return UserViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user = user)
    }

    inner class UserViewHolder(private val binding: Binding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) = with(receiver = binding) {
            idTextView.text = user.id.toString()
            emailTextView.text = user.email
        }
    }
}
