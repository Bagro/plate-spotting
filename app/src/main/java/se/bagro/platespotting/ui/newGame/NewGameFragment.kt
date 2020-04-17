package se.bagro.platespotting.ui.newGame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import se.bagro.platespotting.R
import se.bagro.platespotting.data.GameRepository
import se.bagro.platespotting.model.ClassicGame
import se.bagro.platespotting.model.HybridGame
import se.bagro.platespotting.model.ModernGame


class NewGameFragment : Fragment() {
    private lateinit var viewModel: NewGameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.new_game_fragment, container, false)

        val newClassicGameButton: Button = root.findViewById(R.id.classicGameButton)
        val newHybridGameButton: Button = root.findViewById(R.id.hybrisGameButton)
        val newModernGameButton: Button = root.findViewById(R.id.modernGameButton)
        val cancelButton: Button = root.findViewById(R.id.cancelButton)

        if (!GameRepository.hasOngoingGame()) {
            cancelButton.isVisible = false
        }

        newClassicGameButton.setOnClickListener {
            GameRepository.setCurrentGame(ClassicGame(1))
            findNavController().navigate(R.id.action_newGameFragment_to_navigation_home)
        }

        newHybridGameButton.setOnClickListener{
            GameRepository.setCurrentGame(HybridGame(0, 1))
            findNavController().navigate(R.id.action_newGameFragment_to_navigation_home)
        }

        newModernGameButton.setOnClickListener{
            GameRepository.setCurrentGame(ModernGame(0, 1))
            findNavController().navigate(R.id.action_newGameFragment_to_navigation_home)
        }

        cancelButton.setOnClickListener {
            findNavController().navigate(R.id.action_newGameFragment_to_navigation_dashboard)
        }

        return root
    }
}
