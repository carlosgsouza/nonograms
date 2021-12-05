package carlosgsouza.nonograms

import carlosgsouza.nonograms.carlosgsouza.nonograms.Cell

class GridFactory {

    /**
     * Creates a grid from an input string. The input string contains three required sections (SIZE, ROWS, COLUMNS) and
     * an optional section (VALUES). Example:
     *
     * SIZE
     * 3
     * ROWS
     * 2
     * 2
     * 1
     * COLUMNS
     * 1 1
     * 2
     * 1
     * VALUES
     * x x _
     * _ x ?
     * x _ _
     */
    fun create(text: String): Grid {
        val lines = text.trim().lines().map { it.trim() }.filter { !it.isEmpty() }

        if (lines[0] != "SIZE") throw IllegalArgumentException("SIZE section not found")
        val size = lines[1].toInt()

        val rowsSectionIndex = 2
        if (lines[rowsSectionIndex] != "ROWS") throw IllegalArgumentException("ROWS section not FOUND")
        val rowHints = lines.slice(rowsSectionIndex + 1..rowsSectionIndex + size).map { parseHints(it) }

        val columnsSectionIndex = rowsSectionIndex + 1 + size
        if (lines[columnsSectionIndex] != "COLUMNS") throw IllegalArgumentException("COLUMNS section not FOUND")
        val columnHints = lines.slice(columnsSectionIndex + 1..columnsSectionIndex + size).map { parseHints(it) }

        val valuesSectionIndex = columnsSectionIndex + 1 + size
        if (lines.size <= valuesSectionIndex) return Grid(size, rowHints, columnHints)

        val cells = lines.slice(valuesSectionIndex + 1..valuesSectionIndex + size)
            .map { row -> row.split(" ").map { cell -> Cell.parse(cell) }.toMutableList() }.toMutableList()
        return Grid(size, rowHints, columnHints, cells)
    }

    private fun parseHints(line: String): List<Int> = line.split(" ").map { it.toInt() }
}