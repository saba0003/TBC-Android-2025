package com.example.tbc_android_2025.fragments

import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbc_android_2025.commons.BaseFragment
import com.example.tbc_android_2025.commons.Ids
import com.example.tbc_android_2025.databinding.FragmentViewUsersBinding
import com.example.tbc_android_2025.user.UserAdapter
import com.example.tbc_android_2025.user.UserViewModel
import com.example.tbc_android_2025.utils.IntentKeys
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ViewUsersFragment : BaseFragment<FragmentViewUsersBinding>(inflater = FragmentViewUsersBinding::inflate) {

    private val uvm: UserViewModel by activityViewModels()
    private lateinit var adapter: UserAdapter

    override fun bind() {
        adapter = UserAdapter(onLongClick = { user ->
            // navigate to UpdateFragment, pass user as Parcelable
            val bundle = bundleOf(
                IntentKeys.EXTRA_USER to user,
                IntentKeys.EXTRA_ACTIVE_COUNT to uvm.activeUsersCounter,
                IntentKeys.EXTRA_DELETED_COUNT to uvm.deletedUsersCounter
            )
            findNavController().navigate(resId = Ids.action_viewUsersFragment_to_addUserFragment, args = bundle)
        })

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext()) // <- IMPORTANT
            adapter = this@ViewUsersFragment.adapter
            setHasFixedSize(true)
        }

        // collect tied to view lifecycle to avoid leaks / invalid updates
        viewLifecycleOwner.lifecycleScope.launch {
            uvm.usersFlow.collectLatest { list ->
                adapter.submitList(list)
                binding.emptyText.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
            }
        }
    }

    override fun listeners() {
        setListenerOnBackButton()
    }

    private fun setListenerOnBackButton() {
        binding.backButton.setOnClickListener { findNavController().popBackStack() }
    }
}
