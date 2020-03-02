import assertk.Assert
import assertk.assertions.doesNotContain

fun Assert<Board>.isValidSolutionTo(puzzle: List<Int>) = given { actual ->
    assertThat(actual.squares).doesNotContain(0)

    puzzle.forEachIndexed { index, value ->
        if (value != 0) {
            assertThat(actual.squares[index] == puzzle[index])
        }
    }

    // TODO finish this
}