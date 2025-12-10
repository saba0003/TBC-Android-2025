package com.example.tbc_android_2025.presentation.screens.home

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbc_android_2025.databinding.FragmentHomeBinding as Binding
import com.example.tbc_android_2025.presentation.commons.BaseFragment
import com.example.tbc_android_2025.presentation.extensions.launchAndRepeatOnStart
import com.example.tbc_android_2025.presentation.screens.home.post.PostAdapter
import com.example.tbc_android_2025.presentation.screens.home.post.PostContract.State
import com.example.tbc_android_2025.presentation.screens.home.post.PostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    private val viewModel: PostViewModel by viewModels()
    private val adapter by lazy { PostAdapter() }


    override fun bind() { setupRecyclerView(); collectObservers() }


    /** ======================================= OBSERVERS ======================================= */
    private fun observeStates() = viewModel.state

    private fun collectObservers() {
        viewLifecycleOwner.launchAndRepeatOnStart {
            observeStates().collect { handleStates(group = it) }
        }
    }
    /** ========================================================================================= */


    /** ======================================= HANDLERS ======================================== */
    private fun handleStates(group: State) = with(receiver = group) {
        if (posts.isNotEmpty())
            adapter.submitList(posts)
    }
    /** ========================================================================================= */


    /** ========================================== AUX ========================================== */
    private fun setupRecyclerView() = with(receiver = binding.recyclerView) {
        layoutManager = LinearLayoutManager(context)
        adapter = this@HomeFragment.adapter
        setHasFixedSize(true)
    }
    /** ========================================================================================= */
}
