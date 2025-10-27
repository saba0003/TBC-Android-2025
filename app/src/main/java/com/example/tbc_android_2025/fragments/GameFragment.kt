package com.example.tbc_android_2025.fragments

import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import com.example.tbc_android_2025.commons.BaseFragment
import com.example.tbc_android_2025.commons.Colors
import com.example.tbc_android_2025.commons.Strings
import com.example.tbc_android_2025.databinding.FragmentGameBinding
import com.example.tbc_android_2025.extensions.popMessage

class GameFragment : BaseFragment<FragmentGameBinding>(inflater = FragmentGameBinding::inflate) {

    private val args: GameFragmentArgs by navArgs()
    private var isXTurn = true
    private var totalMoves = 0
    private val gridSize by lazy { args.gridSize }

    private lateinit var board: Array<Array<String?>>


    override fun bind() = binding.run {
        board = Array(gridSize) { arrayOfNulls(gridSize) }
        createGrid(gridSize)
        updateStatusText()
    }

    override fun listeners() = Unit

    private fun createGrid(size: Int) = binding.run {
        gridLayout.apply {
            rowCount = size
            columnCount = size
            removeAllViews()

            post {
                val buttonSize = width / size
                for (row in 0 until size) {
                    for (col in 0 until size) {
                        val btn = Button(requireContext()).apply {
                            textSize = 28f
                            layoutParams =
                                android.view.ViewGroup.LayoutParams(buttonSize, buttonSize)
                            setBackgroundResource(android.R.drawable.btn_default)
                            setOnClickListener {
                                onCellClicked(
                                    button = this,
                                    row = row,
                                    col = col
                                )
                            }
                        }
                        addView(btn)
                    }
                }
            }
        }
    }

    private fun onCellClicked(button: Button, row: Int, col: Int) {
        val symbol = if (isXTurn) getString(Strings.player_x) else getString(Strings.player_o)
        button.text = symbol
        button.setTextColor(
            ContextCompat.getColor(requireContext(), if (isXTurn) Colors.red else Colors.blue)
        )
        button.isEnabled = false

        board[row][col] = symbol
        totalMoves++

        // Check for win
        if (winCondition(row, col, symbol)) {
            binding.root.popMessage(
                text = getString(Strings.player_x_or_o_won_label, symbol),
                color = Colors.light_green
            )
            navigateBack()
            return
        }

        // Check for draw
        if (totalMoves >= gridSize * gridSize) {
            binding.root.popMessage(
                text = getString(Strings.game_ended_in_draw_label),
                color = Colors.gray
            )
            navigateBack()
            return
        }

        // Switch turn
        isXTurn = !isXTurn
        updateStatusText()
    }

    private fun winCondition(row: Int, col: Int, symbol: String): Boolean {
        // Row check
        if ((0 until gridSize).all { board[row][it] == symbol }) return true

        // Column check
        if ((0 until gridSize).all { board[it][col] == symbol }) return true

        // Main diagonal (↘️)
        if (row == col && (0 until gridSize).all { board[it][it] == symbol }) return true

        // Reverse diagonal (↙️)
        if (row + col == gridSize - 1 && (0 until gridSize).all { board[it][gridSize - 1 - it] == symbol }) return true

        return false
    }

    private fun updateStatusText() {
        val current = if (isXTurn) getString(Strings.player_x) else getString(Strings.player_o)
        binding.gameStatusText.text = getString(Strings.determine_player_turn_label, current)
    }
}
