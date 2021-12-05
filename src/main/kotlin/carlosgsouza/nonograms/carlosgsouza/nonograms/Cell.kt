package carlosgsouza.nonograms.carlosgsouza.nonograms

enum class Cell(val character: String) {
    UNKNOWN("?"), BLANK("_"), FILLED("x");

    override fun toString(): String = character

    companion object {
        fun parse(character: String): Cell {
            val cell: Cell? = values().find { it.character == character }
            if (cell == null) throw IllegalArgumentException("Unsupported cell value: $character")
            return cell
        }
    }
}
