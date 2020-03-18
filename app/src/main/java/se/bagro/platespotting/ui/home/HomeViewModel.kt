package se.bagro.platespotting.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import se.bagro.platespotting.model.Game

class HomeViewModel() : ViewModel() {
    var currentGame: Game? = null
    private val _text = MutableLiveData<String>()

    val text: LiveData<String> = _text

    fun setGame(game: Game){
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