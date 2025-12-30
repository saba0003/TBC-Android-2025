package com.example.tbc_android_2025.presentation.screen.home

import androidx.constraintlayout.widget.ConstraintLayout
import com.example.tbc_android_2025.databinding.ItemEquipmentCategoryBinding as Binding
import com.example.tbc_android_2025.presentation.common.BaseAdapter
import com.example.tbc_android_2025.presentation.extension.gone
import com.example.tbc_android_2025.presentation.extension.show

class EquipmentCategoryAdapter :
    BaseAdapter<EquipmentCategoryModel, Binding>(inflater = Binding::inflate) {


    override fun bind(binding: Binding, item: EquipmentCategoryModel) = with(receiver = binding) {
        categoryNameTextView.text = item.name
        setupLevelDots(level = item.level)
        updateTextHorizontalBias(level = item.level)
    }


    /** ===================================== PEAKY BINDERS ===================================== */
    private fun Binding.setupLevelDots(level: UByte) {
        val dots = listOf(dot1View, dot2View, dot3View, dot4View)
        val clampedLevel = level.coerceAtMost(maximumValue = MAX_LEVEL).toInt()
        dots.forEachIndexed { index, view ->
            if (index < clampedLevel)
                view.show() // Show dots up to the level
            else
                view.gone() // Explicitly hide the rest to reset recycled state
        }
    }

    private fun Binding.updateTextHorizontalBias(level: UByte) {
        val clampedLevel = level.coerceAtMost(maximumValue = MAX_LEVEL).toFloat()
        val categoryNameParams = categoryNameTextView.layoutParams as ConstraintLayout.LayoutParams
        categoryNameTextView.layoutParams = categoryNameParams.apply {
            horizontalBias = BASE_BIAS + (clampedLevel * BIAS_STEP)
        }
    }
    /** ========================================================================================= */


    private companion object {
        const val MAX_LEVEL: UByte = 4U
        const val BASE_BIAS: Float = 0.1F
        const val BIAS_STEP: Float = 0.05F
    }
}
