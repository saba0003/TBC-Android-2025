package com.example.tbc_android_2025.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tbc_android_2025.R
import com.example.tbc_android_2025.databinding.ItemUserBinding

typealias Strings = R.string

class UserAdapter(private val onLongClick: (User) -> Unit) : ListAdapter<User, UserAdapter.Holder>(Diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding = binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(user = getItem(position), index = position) // pass position explicitly
    }

    inner class Holder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User, index: Int) = with(receiver = binding) {
            // enumerate starting from 1
            val displayIndex = index + 1

            with(receiver = root) {
                with(receiver = context) {
                    // use string resources with format args
                    userTextView.text = getString(Strings.user_label, displayIndex)
                    val fullName = "${user.firstName} ${user.lastName}"
                    fullNameTextView.text = getString(Strings.full_name_label, fullName)
                    ageTextView.text = getString(Strings.age_label, user.age)
                    emailTextView.text = getString(Strings.email_label, user.email)
                }

                setOnLongClickListener {
                    onLongClick(user)
                    true
                }

                setOnClickListener {
                    // optional
                }
            }
        }
    }

    private object Diff : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem.email.equals(other = newItem.email, ignoreCase = true)

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean = oldItem == newItem
    }
}
