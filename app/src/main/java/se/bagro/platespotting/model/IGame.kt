package se.bagro.platespotting.model

interface IGame {
    fun nextNumber(): String
    fun previousNumber(): String
    fun getType(): GameType
}