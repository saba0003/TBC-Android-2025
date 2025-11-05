package com.example.tbc_android_2025.message

import androidx.recyclerview.widget.DiffUtil.ItemCallback

typealias MessageItemCallback = ItemCallback<Message>

object MessageDiffCallBack : MessageItemCallback() {

    override fun areItemsTheSame(oldMessage: Message, newMessage: Message): Boolean =
        oldMessage == newMessage

    override fun areContentsTheSame(oldMessage: Message, newMessage: Message): Boolean =
        oldMessage.content == newMessage.content

}
