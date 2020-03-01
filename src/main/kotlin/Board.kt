sealed class Board

object Invalid : Board()
data class Complete(override val squares: List<Int>) : Board(), Printable
data class Unresolved(override val squares: List<Int>) : Board(), Printable {

    fun deduce() = Unresolved(
        squares.mapIndexed { index, value ->
            val possibilities = getPossibilities(index)
            if (value != 0 || possibilities.size != 1) {
                value
            } else {
                possibilities.single()
            }
        }
    )

    private fun getPossibilities(index: Int) = (1..9).toSet() - getPeers(index)

    private fun getPeers(index: Int): Set<Int> =
        getRow(index).toSet() + getColumn(index).toSet() + getSubGrid(index).toSet()

    private fun getRow(requestedPosition: Int) =
        squares.filterIndexed { index, _ ->
            rowIndexOf(index) == rowIndexOf(requestedPosition) && index != requestedPosition
        }

    private fun getColumn(requestedPosition: Int) =
        squares.filterIndexed { index, _ ->
            colIndexOf(index) == colIndexOf(requestedPosition) && index != requestedPosition
        }

    private fun getSubGrid(requestedPosition: Int) =
        squares.filterIndexed { index, _ ->
            subGridIndexOf(index) == subGridIndexOf(requestedPosition) && index != requestedPosition
        }

    private fun rowIndexOf(position: Int) = position / 9
    private fun colIndexOf(position: Int) = position % 9
    private fun subGridIndexOf(position: Int) = (colIndexOf(position) / 3) + (3 * (rowIndexOf(position) / 3))
}

interface Printable {
    val squares: List<Int>

    fun print() = run {
        val formattedSquares = squares
            .map { it.toString() }
            .chunked(9)
            .map { it.add(6, "│").add(3, "│") }
            .map { it.joinToString(" ") }
            .add(6, "──────┼───────┼──────")
            .add(3, "──────┼───────┼──────")
            .joinToString("\n", postfix = "\n")

        println(formattedSquares)
    }
}
