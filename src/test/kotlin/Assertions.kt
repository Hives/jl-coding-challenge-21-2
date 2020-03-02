import assertk.Assert
import assertk.assertions.doesNotContain
import assertk.assertions.isInstanceOf

fun Assert<Board>.isValidSolutionTo(puzzle: List<Int>) = given { actual ->
    assertThat(actual).isInstanceOf(Solution::class.java)

    assertThat((actual as Solution).squares).doesNotContain(0)

    puzzle.forEachIndexed { index, value ->
        if (value != 0) {
            assertThat(actual.squares[index] == puzzle[index])
        }
    }



    // TODO finish this
}

private fun Solution.row(index: Int) = this