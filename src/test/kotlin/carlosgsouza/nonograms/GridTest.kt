package carlosgsouza.nonograms

import carlosgsouza.nonograms.carlosgsouza.nonograms.Cell
import com.natpryce.hamkrest.allElements
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
}
