package com.example.tbc_android_2025.fragments

import android.content.res.ColorStateList
import android.graphics.Typeface.NORMAL
import android.graphics.Typeface.BOLD
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tbc_android_2025.outfit.Outfit
import com.example.tbc_android_2025.outfit.OutfitAdapter
import com.example.tbc_android_2025.commons.BaseFragment
import com.example.tbc_android_2025.commons.Colors
import com.example.tbc_android_2025.commons.Outfits
import com.example.tbc_android_2025.databinding.FragmentStoreGalleryBinding
import com.example.tbc_android_2025.utils.CategoryType
import com.example.tbc_android_2025.utils.CategoryType.*

typealias Binding = FragmentStoreGalleryBinding
typealias StoreGalleryFragmentBinding = BaseFragment<Binding>

class StoreGalleryFragment : StoreGalleryFragmentBinding(inflater = Binding::inflate) {

    private val adapter: OutfitAdapter by lazy { OutfitAdapter() }
    private val outfitList by lazy { seed() }
    private var selectedCategoryButton: View? = null
    private var selectedNavbarButton: AppCompatImageButton? = null


    override fun bind() = setup()

    override fun listeners() {
        setListenerOnAllCategoryButton()
        setListenerOnPartyCategoryButton()
        setListenerOnCampingCategoryButton()
        setListenerOnHeartNavbarButton()
        setListenerOnHomeNavbarButton()
        setListenerOnChatNavbarButton()
        setListenerOnBellNavbarButton()
        setListenerOnStarNavbarButton()
    }


    /************************* Category buttons' listeners ****************************************/
    private fun setListenerOnAllCategoryButton() = binding.allCategoryButton.run {
        setOnClickListener {
            updateCategorySelection(newSelectedButton = this)
            showOutfitsByCategory()
        }
    }

    private fun setListenerOnPartyCategoryButton() = binding.partyCategoryButton.run {
        setOnClickListener {
            updateCategorySelection(newSelectedButton = this)
            showOutfitsByCategory(category = PARTY)
        }
    }

    private fun setListenerOnCampingCategoryButton() = binding.campingCategoryButton.run {
        setOnClickListener {
            updateCategorySelection(newSelectedButton = this)
            showOutfitsByCategory(category = CAMPING)
        }
    }
    /**********************************************************************************************/


    /************************* Navbar buttons' listeners ******************************************/
    private fun setListenerOnHeartNavbarButton() = binding.heartButton.run {
        setOnClickListener { updateNavbarSelection(newSelectedButton = this) }
    }

    private fun setListenerOnHomeNavbarButton() = binding.homeButton.run {
        setOnClickListener { updateNavbarSelection(newSelectedButton = this) }
    }

    private fun setListenerOnChatNavbarButton() = binding.chatButton.run {
        setOnClickListener { updateNavbarSelection(newSelectedButton = this) }
    }

    private fun setListenerOnBellNavbarButton() = binding.bellButton.run {
        setOnClickListener { updateNavbarSelection(newSelectedButton = this) }
    }

    private fun setListenerOnStarNavbarButton() = binding.starButton.run {
        setOnClickListener { updateNavbarSelection(newSelectedButton = this) }
    }


    /************************************* AUX ****************************************************/
    private fun setup() = binding.run {
        val recyclerView = recyclerView
        recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = this@StoreGalleryFragment.adapter
        }
        updateCategorySelection(newSelectedButton = allCategoryButton)
        showOutfitsByCategory()
        updateNavbarSelection(newSelectedButton = homeButton)
    }

    private fun updateCategorySelection(newSelectedButton: View) {
        selectedCategoryButton?.let { previous ->
            updateCategoryButtonState(categoryButton = previous, selected = false)
        }
        updateCategoryButtonState(categoryButton = newSelectedButton, selected = true)
        selectedCategoryButton = newSelectedButton
    }

    private fun updateNavbarSelection(newSelectedButton: AppCompatImageButton) {
        val selectedColor =
            ContextCompat.getColor(requireContext(), Colors.category_and_navbar_selected_icon_color)
        selectedNavbarButton?.let { previous -> previous.backgroundTintList = null }
        newSelectedButton.backgroundTintList = ColorStateList.valueOf(selectedColor)
        selectedNavbarButton = newSelectedButton
    }

    private fun updateCategoryButtonState(categoryButton: View, selected: Boolean) {
        val colorRes = if (selected)
            Colors.category_and_navbar_selected_icon_color
        else
            Colors.category_and_navbar_default_icon_color
        val tintColor = ContextCompat.getColor(requireContext(), colorRes)
        val textViews = when (categoryButton) {
            is LinearLayoutCompat -> (0 until categoryButton.childCount)
                .mapNotNull { categoryButton.getChildAt(it) as? TextView }

            is TextView -> listOf(element = categoryButton)
            else -> emptyList()
        }

        categoryButton.backgroundTintList = ColorStateList.valueOf(tintColor)
        textViews.forEach { updateTextStyleAndColor(categoryText = it, selected = selected) }
    }

    private fun updateTextStyleAndColor(categoryText: TextView, selected: Boolean) {
        val defaultTextColor = ContextCompat.getColor(requireContext(), Colors.default_text_color)
        val selectedTextColor = ContextCompat.getColor(requireContext(), Colors.white)

        if (!selected) {
            categoryText.setTextColor(defaultTextColor)
            categoryText.setTypeface(null, NORMAL)
        } else {
            categoryText.setTextColor(selectedTextColor)
            categoryText.setTypeface(null, BOLD)
        }
    }

    private fun showOutfitsByCategory(category: CategoryType = ANY) {
        val filtered = if (category == ANY)
            outfitList
        else
            outfitList.filter { it.category == category }
        adapter.submitList(filtered)
    }

    private fun seed(): List<Outfit> = listOf(
        Outfit(
            image = Outfits.outfit_girl_1,
            label = "Belt suit blazer",
            price = 120
        ),
        Outfit(
            image = Outfits.outfit_girl_2,
            label = "Belt suit blazer",
            price = 120,
            category = PARTY
        ),
        Outfit(
            image = Outfits.outfit_girl_3,
            label = "Belt suit blazer",
            price = 120,
            category = CAMPING
        ),
        Outfit(
            image = Outfits.outfit_girl_4,
            label = "Belt suit blazer",
            price = 120
        )
    )
    /**********************************************************************************************/
}
