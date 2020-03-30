package se.bagro.platespotting.ui.home

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import se.bagro.platespotting.R
import se.bagro.platespotting.data.GameRepository
import se.bagro.platespotting.model.ModernGame

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val licensePlateTextView: TextView = root.findViewById(R.id.lisencePlate)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            licensePlateTextView.text = it
        })

        if (GameRepository.hasOngoingGame()) {
            homeViewModel.setGame(GameRepository.getCurrentGame())
        }
        else {
            homeViewModel.setGame(ModernGame(0, 1))
            GameRepository.setCurrentGame(homeViewModel.currentGame!!)
        }

        licensePlateTextView.setOnClickListener {
            homeViewModel.nextNumber()
            GameRepository.setCurrentGame(homeViewModel.currentGame!!)
        }
        licensePlateTextView.setOnLongClickListener {
            homeViewModel.decrease()
            GameRepository.setCurrentGame(homeViewModel.currentGame!!)
            true
        }

        return root
    }
}