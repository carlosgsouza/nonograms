package carlosgsouza.nonograms

import carlosgsouza.nonograms.carlosgsouza.nonograms.Cell
import carlosgsouza.nonograms.carlosgsouza.nonograms.Cell.BLANK
import carlosgsouza.nonograms.carlosgsouza.nonograms.Cell.FILLED

/**
 * A line is a view of the Grid that corresponds to either a row or a column.
 */
class Line(val grid: Grid, val lineType: LineType, val lineIndex: Int) : Iterable<Cell> {
    enum class LineType { ROW, COLUMN }

    /**
     * Returns a cell from the Grid based on the {@code lineType} and {@code lineIndex}
     */
    operator fun get(cellIndex: Int): Cell {
        if(lineType == LineType.ROW) return grid.cells[lineIndex][cellIndex]
        else return grid.cells[cellIndex][lineIndex]
    }

    /**
     * Sets the value of a cell in the Grid based on the {@code lineType} and {@code lineIndex}
     */
    operator fun set(cellIndex: Int, value: Cell) {
        if(lineType == LineType.ROW) grid.cells[lineIndex][cellIndex] = value
        else grid.cells[cellIndex][lineIndex] = value
    }

    val size: Int
        get() = grid.size

    /**
     * Returns true if a sequence is found for every hint
     */
    val isSolved: Boolean
        get() {
            var sequenceLength = 0
            val hintsIterator = hints.iterator()

            this.forEachIndexed { index, cell ->
                if(cell == FILLED) sequenceLength++

                // Found a sequence when the FILLED cells are over
                if(sequenceLength > 0 && (cell != FILLED || index == size - 1)) {
                    // There are no hints corresponding to this sequence
                    if(!hintsIterator.hasNext()) {
                        return false
                    }

                    // Sequence has a different size
                    if(sequenceLength != hintsIterator.next()) {
                        return false
                    }

                    sequenceLength = 0
                }
            }
            // All hints correspond to a sequence in the line.
            return !hintsIterator.hasNext()
        }

    override fun iterator(): ListIterator<Cell> = LineIterator(this)

    /**
     * Returns the hints for this line based on the {@code lineType} and {@code lineIndex}
     */
    val hints: List<Int>
        get() = if(lineType == LineType.ROW) grid.rowHints[lineIndex] else grid.columnHints[lineIndex]

    /**
     * TODO: Move this somewhere else and keep this as a data class.
     */
    fun solve(): Line {
        fillFromCenter()
        fillCompleteLine()

        return this
    }

    private fun fillFromCenter() {
        if (hints.size != 1) return

        val sequenceLength = hints[0]
        if (sequenceLength <= grid.size / 2) return

        val totalLength = grid.size

        for (i in (totalLength - sequenceLength) until sequenceLength) {
            this[i] = FILLED
        }
    }

    private fun fillCompleteLine() {
        val separatorCount = hints.size - 1
        val filledCount = hints.sum()

        if(separatorCount + filledCount < grid.size) return

        var current = 0
        for(cellCount in hints) {
            repeat(cellCount) {
                this[current++] = FILLED
            }
            // If we reached the end of the line, we do not need to set the next cell to blank.
            if(current < grid.size) {
                this[current++] = BLANK
            }
        }
    }

    override fun toString(): String {
        return """${hints.joinToString(" ")} | ${this.joinToString(" ")}"""
    }
}

internal class LineIterator(val line: Line, var current: Int = 0): ListIterator<Cell> {

    override fun hasNext(): Boolean = current < line.size

    override fun hasPrevious(): Boolean = current > 0

    override fun next(): Cell = line[current++]

    override fun nextIndex(): Int = current + 1

    override fun previous(): Cell = line[current--]

    override fun previousIndex(): Int = current - 1
}