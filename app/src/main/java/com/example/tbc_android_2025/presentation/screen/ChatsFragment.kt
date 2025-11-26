package com.example.tbc_android_2025.presentation.screen

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbc_android_2025.presentation.commons.BaseFragment
import com.example.tbc_android_2025.presentation.commons.Colors
import com.example.tbc_android_2025.presentation.extensions.popMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.example.tbc_android_2025.databinding.FragmentChatsBinding as Binding

@AndroidEntryPoint
class ChatsFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    private val viewModel: ChatsViewModel by viewModels()
    private val adapter by lazy { ChatsAdapter() }


    override fun bind() = setupRecyclerView()

    override fun listeners() {
        viewModel.onEvent(event = ChatsEvent.GetUsers)
    }

    override fun observes() {
        observeState()
        observeSideEffects()
    }


    private fun setupRecyclerView() = with(receiver = binding.recyclerView) {
        adapter = this@ChatsFragment.adapter
        layoutManager = LinearLayoutManager(requireContext())
        setHasFixedSize(true)
    }

    private fun observeState() =
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect {
                adapter.submitList(it.chats)
            }
        }

    private fun observeSideEffects() =
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.sideEffect.collect {
                when (it) {
                    is ChatsSideEffect.ShowError -> {
                        binding.root.popMessage(
                            text = "Something went wrong - ${it.errorMessage}",
                            color = binding.root.context.getColor(Colors.amaranth)
                        )
                    }
                }
            }
        }
}
