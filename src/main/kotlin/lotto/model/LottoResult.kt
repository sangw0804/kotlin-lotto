package lotto.model

import lotto.model.winning.WinningCounter
import java.math.BigDecimal
import java.math.RoundingMode

data class LottoResult(val winningCounter: WinningCounter, private val ticketPriceSum: Money) {
    val benefitRate: Double
        get() {
            val totalPrice = winningCounter.priceSum

            val benefitRate = totalPrice / ticketPriceSum
            return ceilToSecond(benefitRate)
        }

    private fun ceilToSecond(double: Double): Double {
        return BigDecimal(double).setScale(2, RoundingMode.DOWN).toDouble()
    }
}
