package be.hcpl.android.optitripev.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import be.hcpl.android.optitripev.R
import be.hcpl.android.optitripev.databinding.FragmentDashboardBinding
import be.hcpl.android.optitripev.ui.home.HomeViewModel

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: DashboardViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycle.addObserver(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // TODO get all results here
        val optimalSpeedView = binding.resultBestSpeed
        val totalEnergyView = binding.resultTotalTripEnergy

        // TODO show all results here
        viewModel.optimalSpeed.observe(viewLifecycleOwner) { optimalSpeedView.text = getString(R.string.result_best_speed, it)}
        viewModel.totalTripEnergy.observe(viewLifecycleOwner) { totalEnergyView.text = getString(R.string.result_total_trip_energy, it)}

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}