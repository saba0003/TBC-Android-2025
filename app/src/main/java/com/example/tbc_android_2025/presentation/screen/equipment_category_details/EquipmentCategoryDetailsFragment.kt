package com.example.tbc_android_2025.presentation.screen.equipment_category_details

import androidx.navigation.fragment.navArgs
import com.example.tbc_android_2025.databinding.FragmentEquipmentCategoryDetailsBinding as Binding
import com.example.tbc_android_2025.presentation.common.BaseFragment
import com.example.tbc_android_2025.presentation.common.Strings
import kotlin.uuid.ExperimentalUuidApi

class EquipmentCategoryDetailsFragment : BaseFragment<Binding>(inflater = Binding::inflate) {

    private val args: EquipmentCategoryDetailsFragmentArgs by navArgs()


    @OptIn(ExperimentalUuidApi::class)
    override fun bind() = with(receiver = binding) {
        val equipmentCategory = args.equipmentCategory
        uuidTextView.text = getString(Strings.uuid, equipmentCategory.id)
        nameTextView.text = getString(Strings.name, equipmentCategory.name)
        nameDeTextView.text = getString(Strings.name_de, equipmentCategory.nameDe)
        orderIdTextView.text = getString(Strings.order_id, equipmentCategory.orderId)
        superCategoriesTextView.text =
            getString(Strings.super_categories, equipmentCategory.level.toInt())
    }
}
