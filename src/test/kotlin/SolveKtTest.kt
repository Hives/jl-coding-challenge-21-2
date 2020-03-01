import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import org.junit.jupiter.api.Test

internal class SolveKtTest {

    @Test
    fun `should 'solve' unresolved board with all squares completed`() {
        val unresolved = Unresolved(completeBoard)
        val result = solve(unresolved)

        assertThat(result.size).isEqualTo(1)
        assertThat(result.first().squares).isEqualTo(completeBoard)
        assertThat(result.first()).isInstanceOf(Complete::class.java)
    }

    @Test
    fun `should solve unresolved board with one square incomplete`() {
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
        assertThat(result.first().squares).isEqualTo(completeBoard)
        assertThat(result.first()).isInstanceOf(Complete::class.java)

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