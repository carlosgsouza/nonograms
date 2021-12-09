package carlosgsouza.nonograms

import carlosgsouza.nonograms.Cell.*
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class GridTest {
    private val factory = GridFactory()

    @Test
    fun testSolvedGrid() {
        val solvedGrid: Grid = factory.create("""
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
_ x x
x _ ?
""")

        assertTrue(solvedGrid.isSolved)
    }

    @Test
    fun testUnsolvedGrid() {
        val unsolvedGrid: Grid = factory.create("""
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
x x x
x _ ?
""")

        assertFalse(unsolvedGrid.isSolved)
    }

    @Test
    fun testNew() {
        val grid = Grid.new {
            row(1, 1)
            row(2)
            row(1)

            column(1, 1)
            column(1)
            column(2)

            values(FILLED, FILLED, BLANK)
            values(BLANK, FILLED, UNKNOWN)
            values(FILLED, BLANK, BLANK)
        }

        assertThat(grid.size, equalTo(3))

        assertThat(grid.rowHints, equalTo(listOf(listOf(1, 1), listOf(2), listOf(1))))
        assertThat(grid.columnHints, equalTo(listOf(listOf(1, 1), listOf(1), listOf(2))))

        assertThat(grid.rows.size, equalTo(3))
        assertThat(grid.columns.size, equalTo(3))

        assertThat(grid.cells, equalTo(listOf(
            listOf(FILLED, FILLED, BLANK),
            listOf(BLANK, FILLED, UNKNOWN),
            listOf(FILLED, BLANK, BLANK)
        )))
    }
}
