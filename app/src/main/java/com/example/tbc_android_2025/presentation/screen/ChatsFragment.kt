package com.example.tbc_android_2025.presentation.screen

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.tbc_android_2025.presentation.UserAdapter
import com.example.tbc_android_2025.presentation.commons.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.example.tbc_android_2025.databinding.FragmentChatsBinding as Binding

@AndroidEntryPoint
class ChatsFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    private val viewModel: ChatsViewModel by viewModels()
    private val userAdapter by lazy { UserAdapter() }


    override fun bind() = with(receiver = binding) {
        recyclerView.adapter = userAdapter
        recyclerView.setHasFixedSize(true)
    }

    override fun listeners() {
        observeUsers()
        viewModel.loadUsers()
    }

    private fun observeUsers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.users.collect { users ->
                userAdapter.submitList(users)
            }
        }
    }
}
