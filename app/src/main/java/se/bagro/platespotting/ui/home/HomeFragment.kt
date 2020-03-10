package se.bagro.platespotting.ui.home

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import se.bagro.platespotting.R

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

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return null
        val defaultNumber = resources.getInteger(R.integer.saved_default_number_key)
        homeViewModel.setNumber(sharedPref.getInt(getString(R.string.saved_number_key), defaultNumber) ?: defaultNumber)

        val licensePlateTextView: TextView = root.findViewById(R.id.lisencePlate)
        homeViewModel.text.observe(this, Observer {
            licensePlateTextView.text = it
        })

        licensePlateTextView.setOnClickListener {
            homeViewModel.increment()
            with (sharedPref.edit()) {
                putInt(getString(R.string.saved_number_key), homeViewModel.count)
                commit()
            }
        }
        licensePlateTextView.setOnLongClickListener {
            homeViewModel.decrease()
            savedInstanceState?.putInt("number", homeViewModel.count)
            with (sharedPref.edit()) {
                putInt(getString(R.string.saved_number_key), homeViewModel.count)
                commit()
            }
            true
        }

        return root
    }
}