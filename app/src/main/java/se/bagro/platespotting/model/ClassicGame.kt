package se.bagro.platespotting.model

class ClassicGame: IGame {
    override fun nextNumber(): String {
        TODO("Not yet implemented")
    }

    override fun previousNumber(): String {
        TODO("Not yet implemented")
    }

    override fun getType(): GameType {
        return GameType.Classic
    }
}