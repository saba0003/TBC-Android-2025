package com.example.tbc_android_2025.presentation.screen

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbc_android_2025.presentation.commons.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.example.tbc_android_2025.databinding.FragmentChatsBinding as Binding

@AndroidEntryPoint
class ChatsFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    private val viewModel: ChatsViewModel by viewModels()
    private val chatsAdapter by lazy { ChatsAdapter() }


    override fun bind() = setupRecyclerView()

    override fun listeners() {
        observeUsers()
        viewModel.loadUsers()
    }


    private fun setupRecyclerView() = with(receiver = binding.recyclerView) {
        adapter = chatsAdapter
        layoutManager = LinearLayoutManager(requireContext())
        setHasFixedSize(true)
    }

    private fun observeUsers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.chats.collect { chatsAdapter.submitList(it) }
        }
    }
}
