package carlosgsouza.nonograms

import carlosgsouza.nonograms.carlosgsouza.nonograms.Cell

class Grid(
    val size: Int,
    val rowHints: List<List<Int>>,
    val columnHints: List<List<Int>>,
    var cells: MutableList<MutableList<Cell>> = MutableList(size) { MutableList(size) { Cell.UNKNOWN } }
) {
    internal var rows: List<Line> = List(size) { Line(this, Line.LineType.ROW, it) }
    internal var columns: List<Line> = List(size) { Line(this, Line.LineType.COLUMN, it) }
}
