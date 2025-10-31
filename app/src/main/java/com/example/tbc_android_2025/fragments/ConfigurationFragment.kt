package com.example.tbc_android_2025.fragments

import androidx.navigation.fragment.findNavController
import com.example.tbc_android_2025.commons.BaseFragment
import com.example.tbc_android_2025.utils.BoardDimension
import com.example.tbc_android_2025.databinding.FragmentConfigurationBinding as Binding
import com.example.tbc_android_2025.utils.UsefulStrings.SUPPRESS_COMPILER_WARNING

@Suppress(SUPPRESS_COMPILER_WARNING)
private typealias BindingBase = BaseFragment<Binding>

class ConfigurationFragment : BindingBase(inflater = Binding::inflate) {

    override fun bind() = Unit

    override fun listeners() = setListenerOnStartGame()

    private fun setListenerOnStartGame() = binding.run {
        startGameButton.setOnClickListener {
            val selectedDimension = when (dimensionRadioGroup.checkedRadioButtonId) {
                radio3x3.id -> BoardDimension.THREE
                radio4x4.id -> BoardDimension.FOUR
                radio5x5.id -> BoardDimension.FIVE
                else -> BoardDimension.THREE
            }

            val action =
                ConfigurationFragmentDirections.actionConfigurationFragmentToGameFragment(gridSize = selectedDimension.size)
            findNavController().navigate(directions = action)
        }
    }
}
