package se.bagro.platespotting.model

data class ModernGame(var firstDigits: Int, var lastDigit: Int): IGame {
    private val lastDigitMaxValue: Int = 31

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

        return toString();
    }

    override fun previousNumber(): String {
        lastDigit--

        if (lastDigit < 0) {
            if (firstDigits > 0) {
                firstDigits--
                lastDigit = lastDigitMaxValue
            }
            else {
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
            return "0$firstDigits${lastDigitString()}"
        }

        return "$firstDigits${lastDigitString()}"
    }

    private fun lastDigitString(): String =
        when(lastDigit) {
            0 -> "0"
            1 -> "1"
            2 -> "2"
            3 -> "3"
            4 -> "4"
            5 -> "5"
            6 -> "6"
            7 -> "7"
            8 -> "8"
            9 -> "9"
            10 -> "A"
            11 -> "B"
            12 -> "C"
            13 -> "D"
            14 -> "E"
            15 -> "F"
            16 -> "G"
            17 -> "H"
            18 -> "J"
            19 -> "K"
            20 -> "L"
            21 -> "M"
            22 -> "N"
            23 -> "P"
            24 -> "R"
            25 -> "S"
            26 -> "T"
            27 -> "U"
            28 -> "W"
            29 -> "X"
            30 -> "Y"
            31 -> "Z"
            else -> "1"
        }

}
