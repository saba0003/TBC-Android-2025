package com.example.tbc_android_2025.presentation.screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tbc_android_2025.data.extensions.loadChatImage
import com.example.tbc_android_2025.domain.models.Chat
import com.example.tbc_android_2025.databinding.ItemChatBinding as Binding

typealias ChatListAdapter = ListAdapter<Chat, ChatsAdapter.ChatsViewHolder>

class ChatsAdapter : ChatListAdapter(object : ItemCallback<Chat>() {
    override fun areItemsTheSame(oldChat: Chat, newChat: Chat) = oldChat.id == newChat.id
    override fun areContentsTheSame(oldChat: Chat, newChat: Chat) = oldChat == newChat
}) {

    override fun onCreateViewHolder(parent: ViewGroup, ignored: Int): ChatsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = Binding.inflate(inflater, parent, false)
        return ChatsViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: ChatsViewHolder, position: Int) {
        val chat = getItem(position)
        holder.bind(chat = chat)
    }


    inner class ChatsViewHolder(private val binding: Binding) : ViewHolder(binding.root) {

        fun bind(chat: Chat) = with(receiver = binding) {
            with(receiver = chat) {
                fullNameTextView.text = owner
                lastMessageTextView.text = lastMessage
                lastActiveTextView.text = lastActive
                numberOfUnreadMessagesTextView.text = unreadMessages.toString()
                profilePhoto.loadChatImage(url = chat.image)
            }
        }
    }
}
