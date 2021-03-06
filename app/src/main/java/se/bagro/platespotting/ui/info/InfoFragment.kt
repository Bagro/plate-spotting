package se.bagro.platespotting.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import se.bagro.platespotting.R
import se.bagro.platespotting.data.GameRepository
import se.bagro.platespotting.model.Game

class InfoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_info, container, false)

        val newGameButton: Button = root.findViewById(R.id.newGameButton)

        newGameButton.setOnClickListener {
            if (GameRepository.hasOngoingGame()) {
                buildAlertDialog().show()
            } else {
                GameRepository.setCurrentGame(Game(0, 1))
            }

        }

        return root
    }

    private fun buildAlertDialog(): AlertDialog {
        val builder = AlertDialog.Builder(activity!!)

        builder.setCancelable(true)
        builder.setMessage(R.string.ongpong_game_warning)
        builder.setPositiveButton(R.string.yes) { dialog, which ->
            GameRepository.setCurrentGame(Game(0, 1))
            dialog.dismiss()
        }

        builder.setNegativeButton(R.string.no) { dialog, which -> dialog.dismiss() }

        return builder.create()
    }
}