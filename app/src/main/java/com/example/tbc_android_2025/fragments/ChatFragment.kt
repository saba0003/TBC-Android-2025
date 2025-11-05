package com.example.tbc_android_2025.fragments

import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbc_android_2025.commons.BaseFragment
import com.example.tbc_android_2025.databinding.FragmentChatBinding
import com.example.tbc_android_2025.extensions.clearText
import com.example.tbc_android_2025.message.Message
import com.example.tbc_android_2025.message.MessageAdapter

typealias Binding = FragmentChatBinding
typealias BaseBinding = BaseFragment<Binding>

class ChatFragment : BaseBinding(inflater = Binding::inflate) {

    private val messages by lazy { mutableListOf<Message>() }
    private val adapter by lazy { MessageAdapter() }


    override fun bind() {
        setupRecycler()
    }

    override fun listeners() {
        setListenerOnBackButton()
        setListenerOnInputMessageField()
        setListenerOnSendButton()
    }

    private fun setupRecycler() = binding.recyclerView.apply {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = this@ChatFragment.adapter
    }

    private fun setListenerOnBackButton() = binding.backButton.setOnClickListener {
        navigateBack()
    }

    private fun setListenerOnInputMessageField() = with(receiver = binding) {
        inputMessageEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                sendButton.performClick()
                true
            } else false
        }
    }

    private fun setListenerOnSendButton() = with(receiver = binding) {
        sendButton.setOnClickListener {
            val text = inputMessageEditText.text.toString().trim()
            if (text.isNotBlank()) {
                val newMessage = Message(content = text)
                messages.add(element = newMessage)
                adapter.submitList(messages.toList())
                recyclerView.post { recyclerView.scrollToPosition(messages.lastIndex) }
                inputMessageEditText.clearText()
            }
        }
    }
}
