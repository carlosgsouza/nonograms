package carlosgsouza.nonograms

class Grid(
    val size: Int,
    val rowHints: List<List<Int>>,
    val columnHints: List<List<Int>>,
    var cells: MutableList<MutableList<Cell>> = MutableList(size) { MutableList(size) { Cell.UNKNOWN } }
) {
    internal var rows: List<Line> = List(size) { Line(this, Line.LineType.ROW, it) }
    internal var columns: List<Line> = List(size) { Line(this, Line.LineType.COLUMN, it) }

    /**
     * Returns true if the Grid cells satisfy the hints
     */
    val isSolved: Boolean
        get() = rows.all { it.isSolved } && columns.all { it.isSolved }
}
