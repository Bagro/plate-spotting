package se.bagro.platespotting.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel() : ViewModel() {
    var count: Int = 1
    private val _text = MutableLiveData<String>()

    val text: LiveData<String> = _text

    fun setNumber(value: Int) {
        count = value
        setText()
    }

    fun increment() {
        count++

        if (count > 999) {
            count = 999
        }

        setText()
    }

    fun decrease() {
        count--

        if (count < 1) {
            count = 1
        }

        setText()
    }

    private fun setText() {
        when {
            count < 10 -> {
                _text.value = "ABC 00$count"
            }
            count < 100 -> {
                _text.value = "ABC 0$count"
            }
            else -> {
                _text.value = "ABC $count"
            }
        }
    }
}