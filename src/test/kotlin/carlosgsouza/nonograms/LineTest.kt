package carlosgsouza.nonograms

import carlosgsouza.nonograms.carlosgsouza.nonograms.Cell
import carlosgsouza.nonograms.carlosgsouza.nonograms.Cell.BLANK
import com.natpryce.hamkrest.MatchResult
import com.natpryce.hamkrest.Matcher
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class LineTest {
    val gridFactory = GridFactory()

    @Test
    fun testIsSolved() {
        // We only use rows in the test. Ignore the columns hints and cells.
        val grid = gridFactory.create(
            """
            SIZE
            10
            ROWS
            1
            1
            1
            1 1           
            1
            2
            2
            2
            2 2
            2 1
            COLUMNS
            1
            1
            1
            1
            1
            1
            1
            1
            1
            1
            VALUES
            _ _ _ _ _ _ _ _ _ _
            x x _ _ _ _ _ _ _ _
            x _ x _ _ _ _ _ _ _
            _ _ _ x _ _ _ _ _ _            
            _ _ _ _ _ _ _ _ x _
            _ _ _ x x _ _ _ _ _
            x x _ _ _ _ _ _ _ _
            _ _ _ _ _ _ _ _ x x
            _ x x _ _ _ _ _ x x
            x x ? _ x _ _ ? ? _
        """.trimIndent()
        )

        // No sequences
        assertThat(grid.rows[0], isNotSolved)
        // A sequence of different size
        assertThat(grid.rows[1], isNotSolved)
        // More sequences than hints
        assertThat(grid.rows[2], isNotSolved)
        // More hints than sequences
        assertThat(grid.rows[3], isNotSolved)

        // One sequence of size 1 matching one hint of size 1
        assertThat(grid.rows[4], isSolved)
        // One sequence of size 2 matching one hint of size 2
        assertThat(grid.rows[5], isSolved)
        // One sequence at the beginning of the row
        assertThat(grid.rows[6], isSolved)
        // One sequence at the end of the row
        assertThat(grid.rows[7], isSolved)
        // Two sequences
        assertThat(grid.rows[8], isSolved)
        // Two sequences with UNKNOWN cells
        assertThat(grid.rows[9], isSolved)


    }

    private val isSolved = object : Matcher<Line> {
        override val description: String
            get() = "should be solved"

        override fun invoke(actual: Line): MatchResult =
            if (actual.isSolved) MatchResult.Match else MatchResult.Mismatch("$actual is not solved")
    }

    private val isNotSolved = object : Matcher<Line> {
        override val description: String
            get() = "should not be solved"

        override fun invoke(actual: Line): MatchResult =
            if (!actual.isSolved) MatchResult.Match else MatchResult.Mismatch("$actual is solved")
    }
}