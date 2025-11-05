package com.example.tbc_android_2025.fragments


import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbc_android_2025.commons.BaseFragment
import com.example.tbc_android_2025.databinding.FragmentChatBinding
import com.example.tbc_android_2025.extensions.clearText
import com.example.tbc_android_2025.message.MessageAdapter
import com.example.tbc_android_2025.viewmodels.ChatViewModel
import kotlinx.coroutines.launch


typealias Binding = FragmentChatBinding
typealias BaseBinding = BaseFragment<Binding>


class ChatFragment : BaseBinding(inflater = Binding::inflate) {

    private val viewModel: ChatViewModel by viewModels()
    private val adapter by lazy { MessageAdapter() }


    override fun bind() {
        setupRecycler()
        observeMessages()
    }

    override fun listeners() {
        setListenerOnBackButton()
        setListenerOnInputMessageField()
        setListenerOnSendButton()
    }


    /** ===================================== AUX =============================================== */
    private fun setupRecycler() = binding.recyclerView.apply {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = this@ChatFragment.adapter
    }

    private fun observeMessages() = viewLifecycleOwner.lifecycleScope.launch {
        repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            viewModel.messages.collect { list ->
                adapter.submitList(list)
                with(receiver = binding.recyclerView) {
                    post { scrollToPosition(list.lastIndex) }
                }
            }
        }
    }

    private fun setListenerOnBackButton() = binding.backButton.setOnClickListener { navigateBack() }

    private fun setListenerOnInputMessageField() = with(receiver = binding) {
        inputMessageEditText.setOnEditorActionListener { _, actionId, _ ->
            (actionId == EditorInfo.IME_ACTION_SEND).also { shouldSend ->
                if (shouldSend) sendButton.performClick()
            }
        }
    }

    private fun setListenerOnSendButton() = with(receiver = binding) {
        sendButton.setOnClickListener {
            val text = inputMessageEditText.text.toString().trim()
            if (text.isNotBlank()) {
                viewModel.sendMessage(text = text)
                inputMessageEditText.clearText()
            }
        }
    }
    /** ========================================================================================= */
}
