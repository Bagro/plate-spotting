package se.bagro.platespotting.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import se.bagro.platespotting.model.IGame
import se.bagro.platespotting.model.ModernGame

class HomeViewModel() : ViewModel() {
    var currentGame: IGame? = null
    private val _text = MutableLiveData<String>()

    val text: LiveData<String> = _text

    fun setGame(game: IGame){
        currentGame = game
        setText()
    }

    fun nextNumber() {
        currentGame?.nextNumber()
        setText()
    }

    fun decrease() {
        currentGame?.previousNumber()
        setText()
    }

    private fun setText() {
        _text.value = "ABC ${currentGame.toString()}"
    }
}