sealed class Board

object Invalid : Board()

data class Solution(override val squares: List<Int>) : Board(), Printable
data class Unresolved(override val squares: List<Int>) : Board(), Printable {

    private val isValid: Boolean = noSquareHasSameValueAsAPeer() && everyEmptySquareHasAPossibleSolution()

    val isInvalid: Boolean = !isValid

    val isSolution: Boolean = !squares.contains(0) && isValid

    fun toSolution() = Solution(squares)

    fun deduce() = Unresolved(
        squares.mapIndexed { index, value ->
            val possibilities = getPossibilitiesFor(index)
            if (value == 0 && possibilities.size == 1) {
                possibilities.single()
            } else {
                value
            }
        }
    )

    fun unsolvedSquareWithFewestPossibilities(): Int =
        squares
            .mapIndexed { index, value -> Pair(index, value) }
            .filter { (_, value) -> value == 0 }
            .minBy { (index, _) -> getPossibilitiesFor(index).size }
            ?.let { (index, _) ->
                if (getPeersFor(index).size == 9) throw Error("This square has NO possibilities?!")
                index
            }
            ?: throw Error("Couldn't find a square with fewest possibilities?!")

    private fun noSquareHasSameValueAsAPeer(): Boolean =
        squares
            .filterIndexed { index, value -> getPeersFor(index).contains(value) }
            .isEmpty()

    private fun everyEmptySquareHasAPossibleSolution(): Boolean =
        squares
            .mapIndexed { index, value -> Pair(index, value) }
            .filter { (_, value) -> value == 0 }
            .all { (index, _) -> getPossibilitiesFor(index).isNotEmpty() }

    fun getPossibilitiesFor(index: Int) = (1..9).toSet() - getPeersFor(index)

    private fun getPeersFor(index: Int): Set<Int> =
        getRow(index).toSet() + getColumn(index).toSet() + getSubGrid(index).toSet() - setOf(0)

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
