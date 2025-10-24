package com.example.tbc_android_2025.fragments

import android.content.res.ColorStateList
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tbc_android_2025.Outfit
import com.example.tbc_android_2025.OutfitAdapter
import com.example.tbc_android_2025.commons.BaseFragment
import com.example.tbc_android_2025.commons.Colors
import com.example.tbc_android_2025.commons.Drawables
import com.example.tbc_android_2025.databinding.FragmentStoreGalleryBinding
import com.example.tbc_android_2025.utils.CategoryType.*

typealias Binding = FragmentStoreGalleryBinding
typealias StoreGalleryFragmentBinding = BaseFragment<Binding>

class StoreGalleryFragment : StoreGalleryFragmentBinding(inflater = Binding::inflate) {

    private var selectedButton: AppCompatImageButton? = null

    override fun bind() {
        val recyclerView = binding.recyclerView

        val outfitList = listOf(
            Outfit(
                image = Drawables.outfit_girl_1,
                label = "Belt suit blazer",
                price = 120,
                category = ANY
            ),
            Outfit(
                image = Drawables.outfit_girl_2,
                label = "Belt suit blazer",
                price = 120,
                category = PARTY
            ),
            Outfit(
                image = Drawables.outfit_girl_3,
                label = "Belt suit blazer",
                price = 120,
                category = CAMPING
            ),
            Outfit(
                image = Drawables.outfit_girl_4,
                label = "Belt suit blazer",
                price = 120,
                category = ANY
            )
        )

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = OutfitAdapter(outfits = outfitList)

        updateNavbarSelection(newSelectedButton = binding.homeButton)
    }

    override fun listeners() {
        setListenerOnHeartButton()
        setListenerOnHomeButton()
        setListenerOnChatButton()
        setListenerOnBellButton()
        setListenerOnStarButton()
    }

    private fun setListenerOnHeartButton() = binding.run {
        heartButton.setOnClickListener {
            updateNavbarSelection(newSelectedButton = heartButton)
        }
    }

    private fun setListenerOnHomeButton() = binding.run {
        homeButton.setOnClickListener {
            updateNavbarSelection(newSelectedButton = homeButton)
        }
    }

    private fun setListenerOnChatButton() = binding.run {
        chatButton.setOnClickListener {
            updateNavbarSelection(newSelectedButton = chatButton)
        }
    }

    private fun setListenerOnBellButton() = binding.run {
        bellButton.setOnClickListener {
            updateNavbarSelection(newSelectedButton = bellButton)
        }
    }

    private fun setListenerOnStarButton() = binding.run {
        starButton.setOnClickListener {
            updateNavbarSelection(newSelectedButton = starButton)
        }
    }

    private fun updateNavbarSelection(newSelectedButton: AppCompatImageButton) {
        val selectedColor = ContextCompat.getColor(requireContext(), Colors.selected_category)
        val defaultColor =
            ContextCompat.getColor(requireContext(), Colors.category_and_navbar_default_bg_colors)

        selectedButton?.let { previous ->
            ImageViewCompat.setImageTintList(previous, ColorStateList.valueOf(defaultColor))
        }

        ImageViewCompat.setImageTintList(newSelectedButton, ColorStateList.valueOf(selectedColor))

        selectedButton = newSelectedButton
    }
}
