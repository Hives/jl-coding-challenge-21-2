fun solve(board: Unresolved): List<Complete> {
    val newBoard = if (!board.squares.contains(0)) {
        board
    } else {
        board.deduce().deduce()
    }
    return listOf(Complete(newBoard.squares))
}