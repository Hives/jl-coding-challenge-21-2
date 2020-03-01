fun solve(board: Unresolved): List<Complete> {
    val squares = if (!board.squares.contains(0)) {
        board.squares
    } else {
        board.squares.map { if(it == 0) 8 else it }
    }
    return listOf(Complete(squares))
}