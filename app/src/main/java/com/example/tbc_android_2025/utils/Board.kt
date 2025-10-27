package com.example.tbc_android_2025.utils

class Board(private val size: Int) {
    private val grid = Array(size = size) { arrayOfNulls<String>(size = size) }

    operator fun get(row: Int, col: Int): String? = grid[row][col]
    operator fun set(row: Int, col: Int, value: String?) = value.also { grid[row][col] = it }

    fun isFull() = grid.all { row -> row.all { it != null } }
    fun reset() = grid.forEach { it.fill(element = null) }

    fun hasWinner(row: Int, col: Int, symbol: String): Boolean {
        val fullRow = (0 until size).all { grid[row][it] == symbol }
        val fullCol = (0 until size).all { grid[it][col] == symbol }
        val mainDiagonal = row == col && (0 until size).all { grid[it][it] == symbol }
        val antiDiagonal = row + col == size - 1 && (0 until size).all { grid[it][size - 1 - it] == symbol }
        return fullRow || fullCol || mainDiagonal || antiDiagonal
    }
}
