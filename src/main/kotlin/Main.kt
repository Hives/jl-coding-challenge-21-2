fun main() {

}

sealed class Board(val squares: List<Int>)

class Invalid(squares: List<Int>) : Board(squares)
class Complete(squares: List<Int>) : Board(squares)

class Unresolved(squares: List<Int>) : Board(squares) {
    fun getPeers(index: Int): Set<Int> = getRow(index).toSet() + getColumn(index).toSet() + getSubGrid(index).toSet()

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
