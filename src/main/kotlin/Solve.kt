fun solve(squares: List<Int>) = iterateUntilFinished(setOf(Board(squares)))

private tailrec fun iterateUntilFinished(boards: Set<Board>): Set<Board> {
    val newBoards = deduceAndGuessIncompleteBoards(boards)

    return if (newBoards.all { it.isSolution }) {
        newBoards
    } else {
        iterateUntilFinished(newBoards)
    }
}

private fun deduceAndGuessIncompleteBoards(boards: Set<Board>) =
    boards.flatMap { board ->
        if (board.isSolution) {
            setOf(board)
        } else {
            deduceAndGuessBoards(board)
        }
    }.toSet()

private fun deduceAndGuessBoards(board: Board): Set<Board> {
    val newBoard = deduceUntilExhausted(board) ?: return emptySet()

    return if (newBoard.isSolution) {
        setOf(newBoard)
    } else {
        makeAGuess(newBoard)
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