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

typealias ViewUsersBindingFragment = BaseFragment<FragmentViewUsersBinding>

class ViewUsersFragment : ViewUsersBindingFragment(inflater = FragmentViewUsersBinding::inflate) {

    private val uvm: UserViewModel by activityViewModels()
    private lateinit var adapter: UserAdapter

    override fun bind() {
        adapter = UserAdapter(onLongClick = { user ->
            val bundle = bundleOf(
                IntentKeys.EXTRA_USER to user,
                IntentKeys.EXTRA_ACTIVE_COUNT to uvm.activeUsersCounter,
                IntentKeys.EXTRA_DELETED_COUNT to uvm.deletedUsersCounter
            )
            findNavController().navigate(
                resId = Ids.action_viewUsersFragment_to_addUserFragment,
                args = bundle
            )
        })

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@ViewUsersFragment.adapter
            setHasFixedSize(true)
        }

        refreshList()
    }

    override fun listeners() {
        binding.backButton.setOnClickListener { findNavController().popBackStack() }
    }

    private fun refreshList() {
        val list = uvm.getUsers()
        adapter.submitList(newList = list)
        binding.emptyText.visibility =
            if (list.isEmpty())
                View.VISIBLE
            else
                View.GONE
    }
}
