import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isGreaterThan
import assertk.assertions.isInstanceOf
import org.junit.jupiter.api.Test

internal class SolveKtTest {

    @Test
    fun `should 'solve' board with all squares completed`() {
        val puzzle = completeBoard
        val result = solve(puzzle)

        assertThat(result.size).isEqualTo(1)
        assertThat(result.single()).isValidSolutionTo(puzzle)
    }

    @Test
    fun `should solve board with one square incomplete`() {
        val puzzle = listOf(
                1, 2, 3, 4, 5, 6, 7, 8, 9,
                4, 5, 6, 7, 8, 9, 1, 2, 3,
                7, 8, 9, 1, 2, 3, 4, 5, 6,
                2, 3, 4, 5, 6, 7, 8, 9, 1,
                5, 6, 7, 8, 9, 1, 2, 3, 4,
                8, 9, 1, 2, 3, 4, 5, 6, 7,
                3, 4, 5, 6, 7, 8, 9, 1, 2,
                6, 7, 8, 9, 1, 2, 3, 4, 5,
                9, 1, 2, 3, 4, 5, 6, 7, 0
            )
        val result = solve(puzzle)

        assertThat(result.size).isEqualTo(1)
        assertThat(result.single().squares).isEqualTo(completeBoard)
    }

    @Test
    fun `should solve board with two squares incomplete`() {
        val puzzle = listOf(
                1, 2, 3, 4, 5, 6, 7, 8, 9,
                4, 5, 6, 7, 8, 9, 1, 2, 3,
                7, 8, 9, 1, 2, 3, 4, 5, 6,
                2, 3, 4, 5, 6, 7, 8, 9, 1,
                5, 6, 7, 8, 9, 1, 2, 3, 4,
                8, 9, 1, 2, 3, 4, 5, 6, 7,
                3, 4, 5, 6, 7, 8, 9, 1, 2,
                6, 7, 8, 9, 1, 2, 3, 4, 5,
                9, 1, 2, 3, 4, 5, 6, 0, 0
            )
        val result = solve(puzzle)

        assertThat(result.size).isEqualTo(1)
        assertThat(result.single().squares).isEqualTo(completeBoard)
    }

    @Test
    fun `should solve board which requires 2 iterations of deductions`() {
        val puzzle = listOf(
                1, 2, 3, 4, 5, 6, 7, 8, 9,
                4, 5, 6, 7, 8, 9, 1, 2, 3,
                7, 8, 9, 1, 2, 3, 4, 0, 6,
                2, 3, 4, 5, 6, 7, 8, 9, 1,
                5, 6, 7, 8, 9, 1, 2, 3, 4,
                8, 9, 1, 2, 3, 4, 5, 6, 7,
                3, 4, 5, 6, 7, 8, 9, 1, 2,
                6, 7, 8, 9, 1, 2, 3, 0, 0,
                9, 1, 2, 3, 4, 5, 6, 0, 0
            )
        val result = solve(puzzle)

        assertThat(result.size).isEqualTo(1)
        assertThat(result.single().squares).isEqualTo(completeBoard)
    }

    @Test
    fun `should return empty list for a board with no solutions`() {
        val puzzle = listOf(
                1, 2, 3, 4, 5, 6, 7, 8, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 9,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0
            )
        val result = solve(puzzle)

        assertThat(result).isEmpty()
    }

    @Test
    fun `hard puzzle`() {
        val puzzle = listOf(
                0, 0, 0, 0, 7, 4, 3, 1, 6,
                0, 0, 0, 6, 0, 3, 8, 4, 0,
                0, 0, 0, 0, 0, 8, 5, 0, 0,
                7, 2, 5, 8, 0, 0, 0, 3, 4,
                0, 0, 0, 0, 3, 0, 0, 5, 0,
                0, 0, 0, 0, 0, 2, 7, 9, 8,
                0, 0, 8, 9, 4, 0, 0, 0, 0,
                0, 4, 0, 0, 8, 5, 9, 0, 0,
                9, 7, 1, 3, 2, 6, 4, 8, 5
            )
        val result = solve(puzzle)

        assertThat(result.single()).isValidSolutionTo(puzzle)
    }

    @Test
    fun `puzzle with more than one solution`() {
        val puzzle = listOf(
                0, 8, 0, 0, 0, 9, 7, 4, 3,
                0, 5, 0, 0, 0, 8, 0, 1, 0,
                0, 1, 0, 0, 0, 0, 0, 0, 0,
                8, 0, 0, 0, 0, 5, 0, 0, 0,
                0, 0, 0, 8, 0, 4, 0, 0, 0,
                0, 0, 0, 3, 0, 0, 0, 0, 6,
                0, 0, 0, 0, 0, 0, 0, 7, 0,
                0, 3, 0, 5, 0, 0, 0, 8, 0,
                9, 7, 2, 4, 0, 0, 0, 5, 0
            )
        val result = solve(puzzle)

        result.forEach { it.print() }

        assertThat(result.size).isEqualTo(8)
    }

    private val completeBoard = listOf(
        1, 2, 3, 4, 5, 6, 7, 8, 9,
        4, 5, 6, 7, 8, 9, 1, 2, 3,
        7, 8, 9, 1, 2, 3, 4, 5, 6,
        2, 3, 4, 5, 6, 7, 8, 9, 1,
        5, 6, 7, 8, 9, 1, 2, 3, 4,
        8, 9, 1, 2, 3, 4, 5, 6, 7,
        3, 4, 5, 6, 7, 8, 9, 1, 2,
        6, 7, 8, 9, 1, 2, 3, 4, 5,
        9, 1, 2, 3, 4, 5, 6, 7, 8
    )
}