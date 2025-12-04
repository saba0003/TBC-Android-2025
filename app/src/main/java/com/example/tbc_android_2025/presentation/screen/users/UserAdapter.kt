package com.example.tbc_android_2025.presentation.screen.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tbc_android_2025.presentation.extensions.loadImage
import com.example.tbc_android_2025.presentation.screen.users.Users.User
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
            bindProfileImage(url = profileImageUrl)
            bindId(id = id)
            bindFullName(fullName = fullName)
            bindEmail(email = email)
            bindActivationStatus(activationStatus = activationStatus)
            bindLastActiveDescription(lastActiveDescription = lastActiveDescription)
            bindLastActiveEpoch(lastActiveEpoch = lastActiveEpoch)
        }


        /** ====================================== BINDERS ====================================== */
        private fun bindProfileImage(url: String?) {
            binding.profileImageView.loadImage(url = url)
        }

        private fun bindId(id: Int) {
            binding.idTextView.text = id.toString()
        }

        private fun bindFullName(fullName: String) {
            binding.fullNameTextView.text = fullName
        }

        private fun bindEmail(email: String) {
            binding.emailTextView.text = email
        }

        private fun bindActivationStatus(activationStatus: Int) {
            binding.activationStatusTextView.text = activationStatus.toString()
        }

        private fun bindLastActiveDescription(lastActiveDescription: String) {
            binding.lastActiveDescriptionTextView.text = lastActiveDescription
        }

        private fun bindLastActiveEpoch(lastActiveEpoch: Int) {
            binding.lastActiveEpochTextView.text = lastActiveEpoch.toString()
        }
        /** ===================================================================================== */
    }
}
