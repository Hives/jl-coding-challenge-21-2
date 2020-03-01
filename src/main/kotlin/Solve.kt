fun solve(board: Unresolved): List<Solution> {
    val newBoard = if (!board.squares.contains(0)) {
        board
    } else {
        board.deduceUntilExhausted()
    }
    return when(newBoard) {
        is Invalid -> emptyList()
        is Unresolved -> listOf(Solution(newBoard.squares))
        is Solution -> listOf(newBoard)
    }
}