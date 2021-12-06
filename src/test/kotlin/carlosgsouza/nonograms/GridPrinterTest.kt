package carlosgsouza.nonograms

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test

internal class GridPrinterTest {
    private val factory = GridFactory()
    private val printer = GridPrinter()

    @Test
    fun testPrint() {
        val grid: Grid = factory.create("""
SIZE
3
ROWS
1 1
2
1
COLUMNS
1 1
1
2
VALUES
x _ x
_ x x
x _ ?
""")

        assertThat(printer.toString(grid), equalTo("""
                  1    
                  1 1 2
                ┌───────┐
            1 1 │ ■   ■ │
              2 │   ■ ■ │
              1 │ ■   ? │
                └───────┘
                """.trimIndent()))


    }
}