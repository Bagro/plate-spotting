package se.bagro.platespotting.data

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import se.bagro.platespotting.model.*
import java.lang.Exception

object GameRepository {
    private lateinit var preferences: SharedPreferences
    private const val PREFERENCES_FILE_NAME = "PlateSpotting"
    private const val CURRENT_GAME_KEY = "CurrentGame"
    private const val CURRENT_GAME_TYPE_KEY = "CurrentGameType"

    fun with(application: Application) {
        preferences = application.getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)
    }

    fun hasOngoingGame(): Boolean {
        return preferences.getString(CURRENT_GAME_KEY, null) != null
    }

    fun getCurrentGame(): IGame {
        val gameTypeName: String = preferences.getString(CURRENT_GAME_TYPE_KEY, null)
            ?: throw Exception("Game type not found")
        val currentGameJson: String = preferences.getString(CURRENT_GAME_KEY, null) ?: throw Exception("No saved game found")

        val gameType: GameType = GameType.valueOf(gameTypeName)

        if(gameType == GameType.Classic) {
            return GsonBuilder().create().fromJson(currentGameJson, ClassicGame::class.java)
        }

        if(gameType == GameType.Hybrid) {
            return GsonBuilder().create().fromJson(currentGameJson, HybridGame::class.java)
        }

        if(gameType == GameType.Modern) {
            return GsonBuilder().create().fromJson(currentGameJson, ModernGame::class.java)
        }

        throw Exception("No game found")
    }

    fun setCurrentGame(currentGame: IGame) {
        preferences.edit().putString(CURRENT_GAME_TYPE_KEY, currentGame.getType().toString()).apply()
        preferences.edit().putString(CURRENT_GAME_KEY, GsonBuilder().create().toJson(currentGame)).apply()
    }
}