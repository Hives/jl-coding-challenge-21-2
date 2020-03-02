fun solve(squares: List<Int>) = foo(setOf(Board(squares)))

private tailrec fun foo(boards: Set<Board>): Set<Board> {
    val newBoards = boards.flatMap { board ->
        if (board.isSolution) {
            setOf(board)
        } else {
            val newBoard = deduceUntilExhausted(board)
            if (newBoard == null) {
                emptySet()
            } else {
                if (newBoard.isSolution) {
                    setOf(newBoard)
                } else {
                    makeAGuess(newBoard)
                }
            }
        }
    }.toSet()

    return if (newBoards.all { it.isSolution }) {
        newBoards
    } else {
        foo(newBoards)
    }
}

private tailrec fun deduceUntilExhausted(board: Board): Board? {
    val newBoard = board.deduce()

    if (newBoard.isInvalid) return null
    if (newBoard.isSolution) return newBoard
    if (newBoard == board) return newBoard

    return deduceUntilExhausted(newBoard)
}

private fun makeAGuess(board: Board): Set<Board> {
    val index = board.unsolvedSquareWithFewestPossibilities()
    val possibilities = board.getPossibilitiesFor(index)
    return possibilities.map { Board(board.squares.update(index, it)) }.toSet()
}