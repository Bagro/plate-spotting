package se.bagro.platespotting.ui.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import se.bagro.platespotting.R
import se.bagro.platespotting.data.NumberRepository

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var numberRepository: NumberRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        homeViewModel.setNumber(NumberRepository.getCurrentNumber())

        val licensePlateTextView: TextView = root.findViewById(R.id.lisencePlate)
        homeViewModel.text.observe(this, Observer {
            licensePlateTextView.text = it
        })

        licensePlateTextView.setOnClickListener {
            homeViewModel.increment()
            NumberRepository.setCurrentNumber(homeViewModel.count)
        }
        licensePlateTextView.setOnLongClickListener {
            homeViewModel.decrease()
            NumberRepository.setCurrentNumber(homeViewModel.count)
            true
        }

        return root
    }
}