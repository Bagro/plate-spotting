package se.bagro.platespotting.model

data class ClassicGame(var number: Int): IGame {
    override fun nextNumber(): String {
        number++

        if (number > 999) {
            number = 999
        }

        return toString()
    }

    override fun previousNumber(): String {
        number--

        if (number < 1) {
            number = 1
        }

        return toString()
    }

    override fun getType(): GameType {
        return GameType.Classic
    }

    override fun toString(): String {
        if (number <= 99){
            if (number <= 9) {
                return "00$number"
            }

            return "0$number"
        }

        return "00$number"
    }
}