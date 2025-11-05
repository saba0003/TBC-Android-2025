package com.example.tbc_android_2025.message


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tbc_android_2025.commons.DateTimeFormatter.formatDateTime
import com.example.tbc_android_2025.commons.Drawables
import com.example.tbc_android_2025.extensions.dpToPx
import com.example.tbc_android_2025.extensions.isEven
import com.example.tbc_android_2025.extensions.updateConstraints
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
            applyAlignment(position = position)
        }


        /** ==================================== AUX ============================================ */
        private fun setMessage(message: Message) {
            binding.messageTextView.text = message.content
        }

        private fun setSentOn(dateTime: LocalDateTime) {
            binding.sentDateTimeTextView.text = formatDateTime(dateTime = dateTime)
        }

        private fun applyAlignment(position: Int) {
            if (position.isEven())
                alignLeft()
            else
                alignRight()
        }

        private fun alignLeft() = with(receiver = binding) {
            messageTextView.setBackgroundResource(Drawables.left_participant_message_bubble)

            messageTextView.updateConstraints {
                startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                endToEnd = ConstraintLayout.LayoutParams.UNSET
            }

            sentDateTimeTextView.updateConstraints {
                startToStart = messageTextView.id
                endToEnd = ConstraintLayout.LayoutParams.UNSET
                marginStart = 5.dpToPx()
            }
        }

        private fun alignRight() = with(receiver = binding) {
            messageTextView.setBackgroundResource(Drawables.right_participant_message_bubble)

            messageTextView.updateConstraints {
                startToStart = ConstraintLayout.LayoutParams.UNSET
                endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
            }

            sentDateTimeTextView.updateConstraints {
                startToStart = ConstraintLayout.LayoutParams.UNSET
                endToEnd = messageTextView.id
                marginEnd = 5.dpToPx()
            }
        }
        /** ===================================================================================== */
    }
}
