package carlosgsouza.nonograms

import carlosgsouza.nonograms.Cell.BLANK
import carlosgsouza.nonograms.Cell.FILLED

class NonogramSolver(val grid: Grid) {

    fun solve() {
        grid.rows.parallelStream().forEach { fillFromCenter(it) }
        grid.rows.parallelStream().forEach { fillCompleteLine(it) }
        grid.columns.parallelStream().forEach { fillCompleteLine(it) }
        grid.columns.parallelStream().forEach { fillCompleteLine(it) }
    }

    private fun fillFromCenter(line: Line) {
        if (line.hints.size != 1) return

        val sequenceLength = line.hints[0]
        if (sequenceLength <= grid.size / 2) return

        val totalLength = grid.size

        for (i in (totalLength - sequenceLength) until sequenceLength) {
            line[i] = FILLED
        }
    }

    private fun fillCompleteLine(line: Line) {
        val separatorCount = line.hints.size - 1
        val filledCount = line.hints.sum()

        if (separatorCount + filledCount < grid.size) return

        var current = 0
        for (cellCount in line.hints) {
            repeat(cellCount) {
                line[current++] = FILLED
            }
            // If we reached the end of the line, we do not need to set the next cell to blank.
            if (current < grid.size) {
                line[current++] = BLANK
            }
        }
    }
}