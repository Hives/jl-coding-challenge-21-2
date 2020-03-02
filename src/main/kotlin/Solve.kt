fun solve(squares: List<Int>) = foo(setOf(Unresolved(squares)))

private fun foo(boards: Set<Board>): Set<Board> {
    val newBoards: Set<Board> = boards.flatMap { board ->
        if (board is Unresolved) {
            val newBoard = deduceUntilExhausted(board)

            when (newBoard) {
                is Invalid -> emptySet<Board>()
                is Unresolved -> makeAGuess(newBoard)
                is Solution -> setOf(newBoard)
            }
        } else {
            setOf(board)
        }
    }.toSet()

    return if (newBoards.all { it is Solution }) {
        newBoards
    } else {
        foo(newBoards)
    }
}

private tailrec fun deduceUntilExhausted(board: Unresolved): Board {
    val newBoard = board.deduce()

    if (newBoard.isInvalid) return Invalid
    if (newBoard.isSolution) return newBoard.toSolution()
    if (newBoard == board) return newBoard

    return deduceUntilExhausted(newBoard)
}

private fun makeAGuess(board: Unresolved): Set<Unresolved> {
    val index = board.unsolvedSquareWithFewestPossibilities()
    val possibilities = board.getPossibilitiesFor(index)
    return possibilities.map { Unresolved(board.squares.update(index, it)) }.toSet()
}