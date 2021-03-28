package lotto.model.number

import lotto.model.number.LottoNumbers.Companion.CANDIDATE_SIZE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class LottoNumbersTest {
    @ParameterizedTest
    @MethodSource("numbersProvider")
    fun `로또 티켓은 6개의 로또 숫자로 이루어져 있다`(numbers: List<Int>) {
        assertThrows<IllegalArgumentException> {
            LottoNumbers(numbers)
        }
    }

    @ParameterizedTest
    @MethodSource("collectionProvider")
    fun `인자로 주어진 컬렉션이 변해도 LottoNumbers 는 변하지 말아야 한다`(numbers: MutableList<Int>, originalSize: Int) {
        val lottoNumbers = LottoNumbers(numbers)

        assertThat(lottoNumbers.size).isEqualTo(originalSize)
        numbers.add(7)
        assertThat(lottoNumbers.size).isEqualTo(originalSize)
    }

    companion object {
        @JvmStatic
        fun numbersProvider(): List<Arguments> {
            return listOf(
                Arguments {
                    arrayOf(
                        listOf(1, 2, 3, 4, 5)
                    )
                },
                Arguments {
                    arrayOf(
                        listOf(1, 2, 3, 4, 5, 6, 7)
                    )
                }
            )
        }

        @JvmStatic
        fun collectionProvider(): List<Arguments> {
            val numbers = (1..CANDIDATE_SIZE).toList()

            return listOf(
                Arguments {
                    arrayOf(
                        numbers,
                        numbers.size
                    )
                }
            )
        }
    }
}
