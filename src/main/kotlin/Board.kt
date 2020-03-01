sealed class Board

object Invalid : Board()
data class Solution(override val squares: List<Int>) : Board(), Printable
data class Unresolved(override val squares: List<Int>) : Board(), Printable {

    tailrec fun deduceUntilExhausted(): Board {
        val newBoard = deduce()

        if (newBoard.isInvalid) return Invalid
        if (newBoard.isSolution) return newBoard.toSolution()
        if (newBoard == this) return newBoard

        return newBoard.deduceUntilExhausted()
    }

    private fun toSolution() = Solution(squares)

    private fun deduce() = Unresolved(
            squares.mapIndexed { index, value ->
                val possibilities = getPossibilitiesFor(index)
                if (value != 0 || possibilities.size != 1) {
                    value
                } else {
                    possibilities.single()
                }
            }
        )

    private val isValid: Boolean = noSquareHasSameValueAsAPeer() && everyEmptySquareHasAPossibleSolution()

    val isInvalid: Boolean = !isValid

    val isSolution: Boolean = !squares.contains(0) && isValid

    private fun noSquareHasSameValueAsAPeer(): Boolean =
        squares
            .filterIndexed { index, value -> getPeersFor(index).contains(value) }
            .isEmpty()

    private fun everyEmptySquareHasAPossibleSolution(): Boolean =
        squares
            .mapIndexed { index, value -> Pair(index, value) }
            .filter { (_, value) -> value == 0 }
            .all { (index, _) -> getPossibilitiesFor(index).isNotEmpty() }

    private fun getPossibilitiesFor(index: Int) = (1..9).toSet() - getPeersFor(index)

    private fun getPeersFor(index: Int): Set<Int> =
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
