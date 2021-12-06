package carlosgsouza.nonograms

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

internal class CellTest {

    @Test
    fun testToString() {
        assertThat(Cell.UNKNOWN.toString(), equalTo("?"))
        assertThat(Cell.BLANK.toString(), equalTo("_"))
        assertThat(Cell.FILLED.toString(), equalTo("x"))
    }

    @Test
    fun testParse() {
        assertThat(Cell.parse("?"), equalTo(Cell.UNKNOWN))
        assertThat(Cell.parse("_"), equalTo(Cell.BLANK))
        assertThat(Cell.parse("x"), equalTo(Cell.FILLED))

        assertThrows(IllegalArgumentException::class.java) {
            Cell.parse("9")
        }
    }
}