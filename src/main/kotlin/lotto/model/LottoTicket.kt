package lotto.model

import lotto.model.number.LottoNumber
import lotto.model.number.LottoNumbers
import lotto.model.number.WinningNumber
import lotto.model.number.WinningNumbers

class LottoTicket(val candidateNumbers: LottoNumbers) {
    constructor(candidateNumbers: List<Int>) : this(LottoNumbers(candidateNumbers))

    constructor(generator: NumbersGenerator = RandomNumbersGenerator()) : this(
        LottoNumbers(generator.getNumbers(LottoNumber.MAXIMUM, LottoNumbers.CANDIDATE_SIZE))
    )

    fun isMatch(targetNumber: WinningNumber): Boolean {
        return candidateNumbers.contains(targetNumber)
    }

    fun countMatch(targetNumbers: WinningNumbers): Int {
        return candidateNumbers.filter {
            targetNumbers.contains(it)
        }.count()
    }

    companion object {
        val PRICE = Money.THOUSAND
    }
}
