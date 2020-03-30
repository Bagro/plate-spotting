package se.bagro.platespotting.model

import se.bagro.platespotting.util.lastDigitMaxValue
import se.bagro.platespotting.util.lastDigitString

data class ModernGame(var firstDigits: Int, var lastDigit: Int) : IGame {
    override fun nextNumber(): String {
        lastDigit++

        if (lastDigit > lastDigitMaxValue) {
            lastDigit = 0

            firstDigits++

            if (firstDigits > 99) {
                firstDigits = 99
                lastDigit = lastDigitMaxValue
            }
        }

        return toString()
    }

    override fun previousNumber(): String {
        lastDigit--

        if (lastDigit < 0) {
            if (firstDigits > 0) {
                firstDigits--
                lastDigit = lastDigitMaxValue
            } else {
                firstDigits = 0
                lastDigit = 1
            }
        }

        return toString()
    }

    override fun getType(): GameType {
        return GameType.Modern
    }

    override fun toString(): String {
        if (firstDigits <= 9) {
            return "0$firstDigits${lastDigitString(lastDigit)}"
        }

        return "$firstDigits${lastDigitString(lastDigit)}"
    }
}
