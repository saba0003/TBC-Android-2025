package com.example.tbc_android_2025.presentation.screen.users

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbc_android_2025.commons.Colors
import com.example.tbc_android_2025.databinding.FragmentUsersBinding as Binding
import com.example.tbc_android_2025.presentation.commons.BaseFragment
import com.example.tbc_android_2025.presentation.extensions.launchAndRepeatOnStart
import com.example.tbc_android_2025.presentation.extensions.popMessage
import com.example.tbc_android_2025.presentation.screen.users.UsersState.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    private val viewModel: UsersViewModel by viewModels()
    private val adapter by lazy { UserAdapter() }


    override fun bind() {
        with(receiver = binding) {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            collectObservers()
        }
    }


    /** ======================================= OBSERVERS ======================================= */
    private fun observeStates() = viewModel.state

    private fun collectObservers() = viewLifecycleOwner.launchAndRepeatOnStart {
        observeStates().collect { handleStates(group = it) }
    }
    /** ================================================= ======================================= */


    /** ======================================= HANDLERS ======================================== */
    private fun handleStates(group: UsersState) = with(receiver = group) {
        when (this) {
            is Success -> adapter.submitList(data.users)
            is Error -> binding.root.popMessage(text = errorMessage, color = Colors.amaranth)
            is Loader -> Unit
        }
    }
    /** ========================================================================================= */
}