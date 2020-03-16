package se.bagro.platespotting.data

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

object NumberRepository {
    private lateinit var preferences: SharedPreferences
    private const val PREFERENCES_FILE_NAME = "PlateSpotting"
    private const val CURRENT_NUMBER_KEY = "CurrentNumber"

    fun with(application: Application) {
        preferences = application.getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)
    }

    fun hasOngoingGame(): Boolean {
        return getCurrentNumber() > 1
    }

    fun getCurrentNumber(): Int {
        return preferences.getInt(CURRENT_NUMBER_KEY, 1)
    }

    fun setCurrentNumber(currentNumber: Int) {
        preferences.edit().putInt(CURRENT_NUMBER_KEY, currentNumber).apply()
    }
}