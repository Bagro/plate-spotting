package se.bagro.platespotting.model

import se.bagro.platespotting.util.lastDigitMaxValue
import se.bagro.platespotting.util.lastDigitString

data class HybridGame(var firstDigits: Int, var lastDigit: Int) : IGame {
    private var mode: GameType = GameType.Classic

    init {
        mode = if (lastDigit > 9) {
            GameType.Modern
        } else {
            GameType.Classic
        }
    }

    override fun nextNumber(): String {
        lastDigit++

        if (lastDigit > 9 && mode == GameType.Classic) {
            if (firstDigits == 99) {
                firstDigits = 0
                mode = GameType.Modern

                return toString()
            }

            firstDigits++
            lastDigit = 0

            return toString()
        }

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
        if (lastDigit < 10 && mode == GameType.Modern) {
            if (firstDigits == 0){
                firstDigits = 99
                mode = GameType.Classic

                return toString()
            }

            firstDigits--
            lastDigit = lastDigitMaxValue

            return toString()
        }

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
        return GameType.Hybrid
    }

    override fun toString(): String {
        if (firstDigits <= 9) {
            return "0$firstDigits${lastDigitString(lastDigit)}"
        }

        return "$firstDigits${lastDigitString(lastDigit)}"
    }
}