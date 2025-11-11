package com.example.tbc_android_2025.fragments

import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tbc_android_2025.R
import com.example.tbc_android_2025.commons.BaseFragment
import com.example.tbc_android_2025.databinding.FragmentItemBinding
import com.example.tbc_android_2025.item_wrapper.Wrapper
import com.example.tbc_android_2025.item_wrapper.WrapperAdapter
import com.example.tbc_android_2025.item_wrapper.item.Item
import com.example.tbc_android_2025.item_wrapper.item.ItemWrapper
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class ItemFragment : BaseFragment<FragmentItemBinding>(FragmentItemBinding::inflate) {

    private val wrapperAdapter = WrapperAdapter()

    override fun bind() {
        val recyclerView = binding.wrapperRecyclerView
        recyclerView.adapter = wrapperAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val jsonString = """
        [
          [
            {
              "field_id": 1,
              "hint": "UserName",
              "field_type": "input",
              "keyboard": "text",
              "required": false,
              "is_active": true,
              "icon": "https://jemala.png"
            },
            {
              "field_id": 2,
              "hint": "Email",
              "field_type": "input",
              "required": true,
              "keyboard": "text",
              "is_active": true,
              "icon": "https://jemala.png"
            },
            {
              "field_id": 3,
              "hint": "phone",
              "field_type": "input",
              "required": true,
              "keyboard": "number",
              "is_active": true,
              "icon": "https://jemala.png"
            }
          ],
          [
            {
              "field_id": 4,
              "hint": "FullName",
              "field_type": "input",
              "keyboard": "text",
              "required": true,
              "is_active": true,
              "icon": "https://jemala.png"
            },
            {
              "field_id": 14,
              "hint": "Jemali",
              "field_type": "input",
              "keyboard": "text",
              "required": false,
              "is_active": true,
              "icon": "https://jemala.png"
            },
            {
              "field_id": 89,
              "hint": "Birthday",
              "field_type": "chooser",
              "required": false,
              "is_active": true,
              "icon": "https://jemala.png"
            },
            {
              "field_id": 898,
              "hint": "Gender",
              "field_type": "chooser",
              "required": "false",
              "is_active": true,
              "icon": "https://jemala.png"
            }
          ]
        ]

    """.trimIndent()

        val wrappers = parseJsonToWrappers(jsonString)
        wrapperAdapter.submitList(wrappers)
    }

    override fun listeners() {
        // Any click or input listeners
    }

    fun parseJsonToWrappers(json: String): List<ItemWrapper> {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java,
            Types.newParameterizedType(List::class.java, Item::class.java))
        val adapter = moshi.adapter<List<List<Item>>>(type)
        val parsed = adapter.fromJson(json) ?: emptyList()
        return parsed.map { ItemWrapper(it) }
    }
}
