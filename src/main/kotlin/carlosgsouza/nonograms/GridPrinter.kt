package carlosgsouza.nonograms

import carlosgsouza.nonograms.Cell.BLANK
import carlosgsouza.nonograms.Cell.FILLED

class GridPrinter {
    fun toString(grid: Grid) : String {
        val maxRowHints = grid.rowHints.maxOf { it.size }
        val maxColumnHints = grid.columnHints.maxOf { it.size }

        val rows = grid.rows.joinToString("\n") { rowToString(it, maxRowHints) }

        // Each hint takes 2 characters, except for the last one, which takes just 1.
        // The separator has 3 characters.
        val columnHintsIndentation = " ".repeat(maxRowHints * 2 - 1 + 3)
        val columnHints = grid.columns.map { c -> lineToStrings(maxColumnHints, c) }
        val columnHintsString = (0 until maxColumnHints).asSequence()
            .joinToString("\n") { hintIndex -> columnHintsIndentation + columnHints.joinToString(" ") { it[hintIndex] } }

        val horizontalBorderIndentation = " ".repeat(2 * maxRowHints)
        val horizontalBorderLine = "─".repeat(grid.size * 2)
        val horizontalTopBorder = "$horizontalBorderIndentation┌─$horizontalBorderLine┐"
        val horizontalBottomBorder = "$horizontalBorderIndentation└─$horizontalBorderLine┘"

        return listOf(columnHintsString, horizontalTopBorder, rows, horizontalBottomBorder).joinToString("\n")
    }

    fun rowToString(row: Line, maxRowHints: Int): String {
        val hints: List<String> = lineToStrings(maxRowHints, row)
        val cells: List<String> = row.map { cellToString(it) }
        return hints.joinToString(" ") + " │ " + cells.joinToString(" ") + " │"
    }

    private fun lineToStrings(totalSize: Int, line: Line) =
        List(totalSize - line.hints.size) { " " } + line.hints.map { it.toString() }

    fun cellToString(cell: Cell): String {
        return when (cell) {
            BLANK -> " "
            FILLED -> "■"
            else -> "?"
        }
    }
}

