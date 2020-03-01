import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import org.junit.jupiter.api.Test

internal class SolveKtTest {

    @Test
    fun `should 'solve' board with all squares completed`() {
        val unresolved = Unresolved(completeBoard)
        val result = solve(unresolved)

        assertThat(result.size).isEqualTo(1)
        assertThat(result.single().squares).isEqualTo(completeBoard)
        assertThat(result.single()).isInstanceOf(Solution::class.java)
    }

    @Test
    fun `should solve board with one square incomplete`() {
        val unresolved = Unresolved(
            listOf(
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
        )
        val result = solve(unresolved)

        assertThat(result.size).isEqualTo(1)
        assertThat(result.single().squares).isEqualTo(completeBoard)
        assertThat(result.single()).isInstanceOf(Solution::class.java)

    }

    @Test
    fun `should solve board with two squares incomplete`() {
        val unresolved = Unresolved(
            listOf(
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
        )
        val result = solve(unresolved)

        assertThat(result.size).isEqualTo(1)
        assertThat(result.single().squares).isEqualTo(completeBoard)
        assertThat(result.single()).isInstanceOf(Solution::class.java)
    }

    @Test
    fun `should solve board which requires 2 iterations of deductions`() {
        val unresolved = Unresolved(
            listOf(
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
        )
        val result = solve(unresolved)

        assertThat(result.size).isEqualTo(1)
        assertThat(result.single().squares).isEqualTo(completeBoard)
        assertThat(result.single()).isInstanceOf(Solution::class.java)
    }

    @Test
    fun `should return empty list for a board with no solutions`() {
        val unresolved = Unresolved(
            listOf(
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
        )
        val result = solve(unresolved)

        assertThat(result).isEmpty()
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