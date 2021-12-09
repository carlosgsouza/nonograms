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

    companion object {
        /**
         * Builds a new Grid. Example:
         *
         * val grid = Grid.new {
         *   row(1, 1)
         *   row(2)
         *   row(1)
         *
         *   column(1, 1)
         *   column(1)
         *   column(2)
         *
         *   values(Cell.BLANK, Cell.BLANK, Cell.FILLED)
         *   values(Cell.BLANK, Cell.UNKNOWN, Cell.FILLED)
         *   values(Cell.BLANK, Cell.BLANK, Cell.FILLED)
         * }
         */
        fun new(buildFun: GridBuilder.() -> Unit): Grid {
            return GridBuilder.new(buildFun)
        }
    }
}

class GridBuilder {
    val rowHints: MutableList<List<Int>> = mutableListOf()
    val columnHints: MutableList<List<Int>> = mutableListOf()
    val valueRows: MutableList<MutableList<Cell>> = mutableListOf()

    companion object {
        internal fun new(buildFun: GridBuilder.() -> Unit): Grid {
            return GridBuilder().apply(buildFun).build()
        }
    }

    fun row(vararg hints: Int) {
        rowHints.add(hints.asList())
    }

    fun column(vararg hints: Int) {
        columnHints.add(hints.asList())
    }

    fun values(vararg values: Cell) {
        valueRows.add(values.asList().toMutableList())
    }

    private fun build(): Grid {
        if(rowHints.size != columnHints.size) throw IllegalArgumentException("Number of rows and columns is different")
        val size = rowHints.size

        if(valueRows.isEmpty()) {
            return Grid(size, rowHints, columnHints)
        }
        else {
            if(valueRows.size != size) throw IllegalArgumentException("Number of values rows is different")
            valueRows.forEach {
                if(it.size != size) throw IllegalArgumentException("Value row does not have the right number of values")
            }
            return Grid(size, rowHints, columnHints, valueRows)
        }
    }
}
