package com.example.tbc_android_2025.presentation.screen.home

import android.view.View
import com.example.tbc_android_2025.databinding.ItemEquipmentCategoryBinding as Binding
import com.example.tbc_android_2025.presentation.common.BaseAdapter
import com.example.tbc_android_2025.presentation.extension.gone
import com.example.tbc_android_2025.presentation.extension.show

class EquipmentCategoryAdapter :
    BaseAdapter<EquipmentCategoryModel, Binding>(inflater = Binding::inflate) {


    override fun bind(binding: Binding, item: EquipmentCategoryModel) = with(receiver = binding) {
        categoryNameTextView.text = item.name
        setupLevelDots(level = item.level)
    }


    /** ===================================== PEAKY BINDERS ===================================== */
    private fun Binding.setupLevelDots(level: UByte) {
        val dots = listOf(dot1View, dot2View, dot3View, dot4View)
        val clampedLevel = level.coerceAtMost(maximumValue = MAX_LEVEL).toInt()
        dots.take(n = clampedLevel).forEach(action = View::show)
        dots.drop(n = clampedLevel).forEach(action = View::gone)
    }
    /** ========================================================================================= */


    private companion object {
        const val MAX_LEVEL: UByte = 4U
    }
}
