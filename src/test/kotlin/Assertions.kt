import assertk.Assert

fun Assert<Board>.isValidSolutionTo(puzzle: List<Int>) = given { actual ->
    puzzle.forEachIndexed { index, value ->
        if (value != 0) {
            assertThat(actual.squares[index] == puzzle[index])
        }
    }



    // TODO finish this
}