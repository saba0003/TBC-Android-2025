package com.example.tbc_android_2025.fragments

import android.content.res.ColorStateList
import android.graphics.Typeface.NORMAL
import android.graphics.Typeface.BOLD
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
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

    private var selectedCategoryButton: View? = null
    private var selectedNavbarButton: AppCompatImageButton? = null

    private val outfitList = listOf(
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

    override fun bind() = binding.run {
        val recyclerView = recyclerView
        recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = OutfitAdapter(outfits = outfitList)
        }
        updateCategorySelection(newSelectedButton = allCategoryButton)
        updateNavbarSelection(newSelectedButton = homeButton)
    }

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
        setOnClickListener { updateCategorySelection(newSelectedButton = this) }
    }

    private fun setListenerOnPartyCategoryButton() = binding.partyCategoryButton.run {
        setOnClickListener { updateCategorySelection(newSelectedButton = this) }
    }

    private fun setListenerOnCampingCategoryButton() = binding.campingCategoryButton.run {
        setOnClickListener { updateCategorySelection(newSelectedButton = this) }
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
    /**********************************************************************************************/


    /** AUX */
    private fun updateCategorySelection(newSelectedButton: View) {
        selectedCategoryButton?.let { previous ->
            updateCategoryButtonState(categoryButton = previous, selected = false)
        }
        updateCategoryButtonState(categoryButton = newSelectedButton, selected = true)
        selectedCategoryButton = newSelectedButton
    }

    /** AUX */
    private fun updateNavbarSelection(newSelectedButton: AppCompatImageButton) {
        val selectedColor =
            ContextCompat.getColor(requireContext(), Colors.category_and_navbar_selected_icon_color)
        selectedNavbarButton?.let { previous -> previous.backgroundTintList = null }
        newSelectedButton.backgroundTintList = ColorStateList.valueOf(selectedColor)
        selectedNavbarButton = newSelectedButton
    }

    /** AUX */
    private fun updateCategoryButtonState(categoryButton: View, selected: Boolean) {
        val colorRes = if (selected)
            Colors.category_and_navbar_selected_icon_color
        else
            Colors.category_and_navbar_default_icon_color
        val tintColor = ContextCompat.getColor(requireContext(), colorRes)

        categoryButton.backgroundTintList = ColorStateList.valueOf(tintColor)
        if (categoryButton is LinearLayoutCompat) {
            for (i in 0 until categoryButton.childCount) {
                val child = categoryButton.getChildAt(i)
                if (child is AppCompatTextView)
                    updateTextStyleAndColor(categoryText = child, selected = selected)
            }
        } else if (categoryButton is AppCompatButton) {
            updateTextStyleAndColor(categoryText = categoryButton, selected = selected)
        }
    }

    /** AUX */
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
}
