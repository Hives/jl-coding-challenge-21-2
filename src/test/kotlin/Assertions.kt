import assertk.Assert
import assertk.assertions.doesNotContain
import assertk.assertions.isTrue

fun Assert<Board>.isValidSolutionTo(puzzle: List<Int>) = given { actual ->
    assertThat(actual.squares).doesNotContain(0)

    puzzle.forEachIndexed { index, value ->
        if (value != 0) {
            assertThat(actual.squares[index] == puzzle[index])
        }
    }

    assertThat(actual.isSolution).isTrue()
}