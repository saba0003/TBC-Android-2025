package com.example.tbc_android_2025.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil3.imageLoader
import coil3.load
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.placeholder
import coil3.request.target
import com.example.tbc_android_2025.data.User
import com.example.tbc_android_2025.presentation.commons.Drawables
import com.example.tbc_android_2025.databinding.ItemUserBinding as Binding

typealias UserListAdapter = ListAdapter<User, UserAdapter.UserViewHolder>

class UserAdapter : UserListAdapter(userDiffCallBack) {

    companion object {
        private val userDiffCallBack = object : ItemCallback<User>() {
            override fun areItemsTheSame(oldUser: User, newUser: User) = oldUser.id == newUser.id
            override fun areContentsTheSame(oldUser: User, newUser: User) = oldUser == newUser
        }
    }


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

        fun bind(user: User) = with(receiver = binding) {
            with(receiver = user) {
                fullNameTextView.text = owner
                lastMessageTextView.text = lastMessage
                lastActiveTextView.text = lastActive
                numberOfUnreadMessagesTextView.text = unreadMessages.toString()
//                val request = ImageRequest.Builder(context = profilePhoto.context)
//                    .data(data = image)
//                    .crossfade(enable = true)
//                    .target(imageView = profilePhoto)
//                    .build()
//                profilePhoto.context.imageLoader.enqueue(request = request)

                profilePhoto.load(data = image) {
                    crossfade(enable = true)
                    placeholder(drawableResId = Drawables.ic_launcher_background)
                    listener(
                        onError = { _, e ->
                            Log.e("Coil", "Failed to load image", e.throwable)
                        },
                        onSuccess = { _, _ ->
                            Log.d("Coil", "Image loaded successfully")
                        }
                    )
                    error(message = Drawables.ic_launcher_foreground)
                }
            }
        }
    }
}