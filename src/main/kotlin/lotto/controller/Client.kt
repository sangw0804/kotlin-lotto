package lotto.controller

import lotto.model.LottoChecker
import lotto.model.LottoStore
import lotto.model.Money
import lotto.model.WinningNumbers
import lotto.view.InputView
import lotto.view.OutputView

fun main() {
    val moneyAmount = Money(InputView.readLine("구입금액을 입력해 주세요.").toInt())

    val store = LottoStore()
    val tickets = store.buy(moneyAmount)

    OutputView.printTickets(tickets)

    val winningNumbers = WinningNumbers(InputView.readLine("지난 주 당첨 번호를 입력해 주세요.").map { it.toInt() })

    val lottoChecker = LottoChecker(winningNumbers)
    val result = lottoChecker.check(tickets, moneyAmount)

    OutputView.printResult(result)
}
