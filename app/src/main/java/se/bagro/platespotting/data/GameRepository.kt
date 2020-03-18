package se.bagro.platespotting.data

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import se.bagro.platespotting.model.Game

object GameRepository {
    private lateinit var preferences: SharedPreferences
    private const val PREFERENCES_FILE_NAME = "PlateSpotting"
    private const val CURRENT_NUMBER_KEY = "CurrentNumber"

    fun with(application: Application) {
        preferences = application.getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)
    }

    fun hasOngoingGame(): Boolean {
        return preferences.getString(CURRENT_NUMBER_KEY, null) != null
    }

    fun getCurrentGame(): Game {
        return GsonBuilder().create().fromJson(preferences.getString(CURRENT_NUMBER_KEY, null), Game::class.java)
    }

    fun setCurrentGame(currentGame: Game) {
        preferences.edit().putString(CURRENT_NUMBER_KEY, GsonBuilder().create().toJson(currentGame)).apply()
    }
}