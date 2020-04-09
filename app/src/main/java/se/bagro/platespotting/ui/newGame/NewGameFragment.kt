package se.bagro.platespotting.ui.newGame

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.isVisible
import se.bagro.platespotting.R
import se.bagro.platespotting.data.GameRepository
import se.bagro.platespotting.model.HybridGame
import se.bagro.platespotting.model.ModernGame
import se.bagro.platespotting.ui.home.HomeFragment


class NewGameFragment : Fragment() {
    private lateinit var viewModel: NewGameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.new_game_fragment, container, false)

        val fragmentManager = activity!!.supportFragmentManager

        val newClassicGameButton: Button = root.findViewById(R.id.classicGameButton)
        val newHybridGameButton: Button = root.findViewById(R.id.hybrisGameButton)
        val newModernGameButton: Button = root.findViewById(R.id.modernGameButton)
        val cancelButton: Button = root.findViewById(R.id.cancelButton)

        if (fragmentManager.backStackEntryCount == 0) {
            cancelButton.isVisible = false
        }

        newClassicGameButton.setOnClickListener {
            GameRepository.setCurrentGame(ModernGame(0, 1))
            fragmentManager.beginTransaction().replace(R.id.container, HomeFragment()).commit()
        }

        newHybridGameButton.setOnClickListener{
            GameRepository.setCurrentGame(HybridGame(0, 1))
        }

        newModernGameButton.setOnClickListener{
            GameRepository.setCurrentGame(ModernGame(0, 1))
        }

        cancelButton.setOnClickListener {

            fragmentManager.popBackStackImmediate()
        }

        return root
    }
}
