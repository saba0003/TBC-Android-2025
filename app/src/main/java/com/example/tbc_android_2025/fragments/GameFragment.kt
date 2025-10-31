package com.example.tbc_android_2025.fragments

import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import com.example.tbc_android_2025.commons.BaseFragment
import com.example.tbc_android_2025.commons.Colors
import com.example.tbc_android_2025.commons.Strings
import com.example.tbc_android_2025.databinding.FragmentGameBinding as Binding
import com.example.tbc_android_2025.extensions.popMessage
import com.example.tbc_android_2025.utils.Board
import com.example.tbc_android_2025.utils.UsefulStrings.SUPPRESS_COMPILER_WARNING

@Suppress(SUPPRESS_COMPILER_WARNING)
private typealias BindingBase = BaseFragment<Binding>

class GameFragment : BindingBase(inflater = Binding::inflate) {

    private val args: GameFragmentArgs by navArgs()
    private val gridSize by lazy { args.gridSize }
    private var isXTurn = true

    private lateinit var board: Board


    override fun bind() {
        board = Board(size = gridSize)
        createGrid()
        updateStatusText()
    }

    override fun listeners() = Unit

    private fun createGrid() = binding.gridLayout.run {
        rowCount = gridSize
        columnCount = gridSize
        removeAllViews()

        post {
            val buttonSize = width / gridSize
            repeat(times = gridSize) { row ->
                repeat(times = gridSize) { col ->
                    addView(createCellButton(row = row, col = col, size = buttonSize))
                }
            }
        }
    }

    private fun createCellButton(row: Int, col: Int, size: Int) = AppCompatButton(requireContext()).apply {
        textSize = 28f
        layoutParams = ViewGroup.LayoutParams(size, size)
        setBackgroundResource(android.R.drawable.btn_default)
        setOnClickListener { onCellClicked(button = this, row = row, col = col) }
    }

    private fun onCellClicked(button: AppCompatButton, row: Int, col: Int) {
        val symbol = currentSymbol()
        markCell(button, symbol)
        board[row, col] = symbol
        handleGameState(row, col, symbol)
    }

    private fun markCell(button: AppCompatButton, symbol: String) = button.apply {
        text = symbol
        isEnabled = false
        setTextColor(
            ContextCompat.getColor(
                requireContext(),
                if (symbol == getString(Strings.player_X)) Colors.red else Colors.blue
            )
        )
    }

    private fun handleGameState(row: Int, col: Int, symbol: String) {
        when {
            board.hasWinner(row, col, symbol) -> showEndMessage(
                text = getString(Strings.player_x_or_o_won_label, symbol),
                color = if (symbol == getString(Strings.player_X)) Colors.red else Colors.blue
            )

            board.isFull() -> showEndMessage(
                text = getString(Strings.game_ended_in_draw_label),
                color = Colors.gray
            )

            else -> switchTurn()
        }
    }

    private fun switchTurn() {
        isXTurn = !isXTurn
        updateStatusText()
    }

    private fun currentSymbol() = if (isXTurn) getString(Strings.player_X) else getString(Strings.player_O)

    private fun updateStatusText() {
        binding.gameStatusText.text = getString(Strings.determine_player_turn_label, currentSymbol())
    }

    private fun showEndMessage(text: String, color: Int) {
        binding.root.popMessage(text = text, color = color)
        navigateBack()
    }
}
