package com.example.tbc_android_2025.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tbc_android_2025.R
import com.example.tbc_android_2025.databinding.ItemUserBinding

typealias Strings = R.string
typealias BaseUserAdapter = RecyclerView.Adapter<UserAdapter.Holder>

class UserAdapter(private val onLongClick: (User) -> Unit) : BaseUserAdapter() {

    private val items = mutableListOf<User>()

    fun submitList(newList: List<User>) {
        items.clear()
        items.addAll(elements = newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding = binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(user = items[position], index = position)
    }

    override fun getItemCount(): Int = items.size

    inner class Holder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User, index: Int) = with(receiver = binding) {
            val displayIndex = index + 1

            with(receiver = user) {
                with(receiver = root.context) {
                    userTextView.text = getString(Strings.user_label, displayIndex)
                    fullNameTextView.text =
                        getString(Strings.full_name_label, "$firstName $lastName")
                    ageTextView.text = getString(Strings.age_label, age)
                    emailTextView.text = getString(Strings.email_label, email)
                }

                root.setOnLongClickListener {
                    onLongClick(this)
                    true
                }
            }
        }
    }
}
