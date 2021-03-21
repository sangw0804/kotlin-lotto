package lotto.model

import lotto.model.number.CandidateNumbers
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class LottoStoreTest {
    private val store = LottoStore(RandomNumbersGenerator())

    @ParameterizedTest
    @MethodSource("buyTicketCountProvider")
    fun `상점에 돈을 주고 로또를 사면 1000원 당 한장의 LottoTicket 을 준다`(money: Money, ticketCount: Int) {
        val result = store.buy(money)

        assertThat(result.size).isEqualTo(ticketCount)
    }

    @ParameterizedTest
    @MethodSource("buyCandidateNumbersTicketsProvider")
    fun `수동으로 사고싶은 만큼 CandidateNumbers를 주면 알맞은 LottoTicket을 준다`(
        money: Money,
        listOfCandidateNumbers: List<CandidateNumbers>,
        lottoTickets: LottoTickets
    ) {
        val result = store.buy(money, listOfCandidateNumbers)

        assertThat(result.size).isEqualTo(lottoTickets.size)
        assertThat(result[0].candidateNumbers).isEqualTo(lottoTickets[0].candidateNumbers)
        assertThat(result[1].candidateNumbers).isEqualTo(lottoTickets[1].candidateNumbers)
    }

    companion object {
        @JvmStatic
        fun buyTicketCountProvider(): List<Arguments> {
            return listOf(
                Arguments { arrayOf(Money.THOUSAND, 1) },
                Arguments { arrayOf(Money(3000), 3) },
                Arguments { arrayOf(Money(2500), 2) },
                Arguments { arrayOf(Money.ZERO, 0) }
            )
        }

        @JvmStatic
        fun buyCandidateNumbersTicketsProvider(): List<Arguments> {
            val listOfCandidateNumbers = listOf(
                CandidateNumbers(listOf(1, 2, 3, 4, 5, 6)),
                CandidateNumbers(listOf(7, 8, 9, 10, 11, 12))
            )

            return listOf(
                Arguments {
                    arrayOf(
                        Money(5000),
                        listOfCandidateNumbers,
                        LottoTickets(
                            listOf(
                                LottoTicket(listOfCandidateNumbers[0]),
                                LottoTicket(listOfCandidateNumbers[1]),
                                LottoTicket(),
                                LottoTicket(),
                                LottoTicket()
                            )
                        )
                    )
                }
            )
        }
    }
}
