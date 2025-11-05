package com.example.tbc_android_2025.message

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tbc_android_2025.commons.DateTimeFormatter.formatDateTime
import com.example.tbc_android_2025.commons.Drawables
import java.time.LocalDateTime
import com.example.tbc_android_2025.databinding.ItemMessageBinding as Binding

typealias MessageListAdapter = ListAdapter<Message, MessageAdapter.MessageViewHolder>

class MessageAdapter : MessageListAdapter(MessageDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, ignored: Int): MessageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = Binding.inflate(inflater, parent, false)
        return MessageViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) =
        holder.bind(message = getItem(position), position = position)

    inner class MessageViewHolder(private val binding: Binding) : ViewHolder(binding.root) {

        fun bind(message: Message, position: Int) = with(receiver = message) {
            setMessage(message = this)
            setSentOn(dateTime = sentOn)
            foo(position = position)
        }

        private fun setMessage(message: Message) {
            binding.messageTextView.text = message.content
        }

        private fun setSentOn(dateTime: LocalDateTime) {
            binding.sentDateTimeTextView.text = formatDateTime(dateTime = dateTime)
        }

        private fun foo(position: Int) = with(receiver = binding) {
            val paramsMessage = messageTextView.layoutParams as ConstraintLayout.LayoutParams
            val paramsTime = sentDateTimeTextView.layoutParams as ConstraintLayout.LayoutParams

            if (position % 2 == 0) { // 👈 even → left
                messageTextView.setBackgroundResource(Drawables.left_participant_message_bubble)

                paramsMessage.apply {
                    startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                    endToEnd = ConstraintLayout.LayoutParams.UNSET
                }

                paramsTime.apply {
                    startToStart = messageTextView.id
                    endToEnd = ConstraintLayout.LayoutParams.UNSET
                }

            } else { // 👈 odd → right
                messageTextView.setBackgroundResource(Drawables.right_participant_message_bubble)

                paramsMessage.apply {
                    endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                    startToStart = ConstraintLayout.LayoutParams.UNSET
                }

                paramsTime.apply {
                    endToEnd = messageTextView.id
                    startToStart = ConstraintLayout.LayoutParams.UNSET
                }
            }

            messageTextView.layoutParams = paramsMessage
            sentDateTimeTextView.layoutParams = paramsTime
        }
    }
}
