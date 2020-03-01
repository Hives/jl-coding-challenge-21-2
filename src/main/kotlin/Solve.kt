fun solve(board: Unresolved): List<Solution> {
    val newBoard = board.deduceUntilExhausted()

    return when(newBoard) {
        is Invalid -> emptyList()
        is Unresolved -> listOf(Solution(newBoard.squares))
        is Solution -> listOf(newBoard)
    }
}