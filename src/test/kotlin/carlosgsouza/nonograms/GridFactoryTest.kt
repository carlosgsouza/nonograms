package carlosgsouza.nonograms

import carlosgsouza.nonograms.carlosgsouza.nonograms.Cell
import com.natpryce.hamkrest.allElements
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test

internal class GridFactoryTest {
    private val factory = GridFactory()

    @Test
    fun testCreateFromString_BlankValues() {
        val grid: Grid = factory.create("""
SIZE
3
ROWS
2
2
1
COLUMNS
1 1
2
1
""")

        assertThat(grid.size, equalTo(3))

        assertThat(grid.rowHints, equalTo(listOf(listOf(2), listOf(2), listOf(1))))
        assertThat(grid.columnHints, equalTo(listOf(listOf(1, 1), listOf(2), listOf(1))))

        assertThat(grid.rows.size, equalTo(3))
        assertThat(grid.columns.size, equalTo(3))

        assertThat(grid.rows.map { it.iterator().asSequence().toList() }, allElements(equalTo(listOf(Cell.UNKNOWN, Cell.UNKNOWN, Cell.UNKNOWN))))
        assertThat(grid.columns.map { it.iterator().asSequence().toList() }, allElements(equalTo(listOf(Cell.UNKNOWN, Cell.UNKNOWN, Cell.UNKNOWN))))
    }

    @Test
    fun testCreateFromString_InitializedValues() {
        val grid: Grid = factory.create("""
SIZE
3
ROWS
2
2
1
COLUMNS
1 1
2
1
VALUES
x x _
_ x ?
x _ _
""")

        assertThat(grid.size, equalTo(3))

        assertThat(grid.rowHints, equalTo(listOf(listOf(2), listOf(2), listOf(1))))
        assertThat(grid.columnHints, equalTo(listOf(listOf(1, 1), listOf(2), listOf(1))))

        assertThat(grid.rows.size, equalTo(3))
        assertThat(grid.columns.size, equalTo(3))

        assertThat(grid.cells, equalTo(listOf(
            listOf(Cell.FILLED, Cell.FILLED, Cell.BLANK),
            listOf(Cell.BLANK, Cell.FILLED, Cell.UNKNOWN),
            listOf(Cell.FILLED, Cell.BLANK, Cell.BLANK),
        )))
    }
}
