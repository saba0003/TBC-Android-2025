package com.example.tbc_android_2025.presentation.screen.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tbc_android_2025.presentation.extensions.loadUserAvatarImage
import com.example.tbc_android_2025.databinding.ItemUserBinding as Binding

typealias UserListAdapter = ListAdapter<User, UserAdapter.UserViewHolder>

class UserAdapter : UserListAdapter(object : ItemCallback<User>() {
    override fun areItemsTheSame(oldUser: User, newUser: User) = oldUser.id == newUser.id
    override fun areContentsTheSame(oldUser: User, newUser: User) = oldUser == newUser
}) {


    override fun onCreateViewHolder(parent: ViewGroup, ignored: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = Binding.inflate(inflater, parent, false)
        return UserViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user = user)
    }


    inner class UserViewHolder(private val binding: Binding) : ViewHolder(binding.root) {

        fun bind(user: User) = with(receiver = user) {
            bindAvatar(url = avatar)
            bindId(id = id)
            bindEmail(email = email)
            bindFirstName(firstName = firstName)
            bindLastName(lastName = lastName)
        }


        /** =================================== AUX ============================================= */
        private fun bindAvatar(url: String) {
            binding.avatarImageView.loadUserAvatarImage(url = url)
        }

        private fun bindId(id: Int) {
            binding.idTextView.text = id.toString()
        }

        private fun bindEmail(email: String) {
            binding.emailTextView.text = email
        }

        private fun bindFirstName(firstName: String) {
            binding.firstNameTextView.text = firstName
        }

        private fun bindLastName(lastName: String) {
            binding.lastNameTextView.text = lastName
        }
        /** ===================================================================================== */
    }
}