package be.hcpl.android.optitripev.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import be.hcpl.android.optitripev.R
import be.hcpl.android.optitripev.databinding.FragmentDashboardBinding
import be.hcpl.android.optitripev.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: ResultViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycle.addObserver(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this).get(ResultViewModel::class.java)

        _binding = FragmentResultBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // get all results here
        val optimalSpeedView = binding.resultBestSpeed
        val totalEnergyView = binding.resultTotalTripEnergy
        val numberOfChargesView = binding.resultNumberOfCharges
        val totalTripTimeView = binding.resultTotalTripTime
        val totalDriveTimeView = binding.resultDrivingTime
        val totalChargeTimeView = binding.resultTotalChargeTime
        val equivalentSpeedView = binding.resultFinalSpeed
        // FIXME this one is missing val equivalentSpeedView = binding.resultChargeEquivSpeed
        val timePerChargeView = binding.resultTimePerCharge
        val totalTimePerChargeView = binding.resultTotalTimePerCharge
        val distanceFirstCharger = binding.resultDistanceCharge1
        val distanceSecondCharger = binding.resultDistanceCharge2
        val distanceThirdCharger = binding.resultDistanceCharge3

        // show all results here
        viewModel.optimalSpeed.observe(viewLifecycleOwner) { optimalSpeedView.text = getString(R.string.result_best_speed, it)}
        viewModel.totalTripEnergy.observe(viewLifecycleOwner) { totalEnergyView.text = getString(R.string.result_total_trip_energy, it)}
        viewModel.numberOfCharges.observe(viewLifecycleOwner) { numberOfChargesView.text = getString(R.string.result_number_of_charges, it)}
        viewModel.totalTripTime.observe(viewLifecycleOwner) { totalTripTimeView.text = getString(R.string.result_total_trip_time, it)}
        viewModel.totalChargeTime.observe(viewLifecycleOwner) { totalChargeTimeView.text = getString(R.string.result_total_charge_time, it)}
        viewModel.totalDriveTime.observe(viewLifecycleOwner) { totalDriveTimeView.text = getString(R.string.result_total_driving_time, it)}
        viewModel.equivalentSpeed.observe(viewLifecycleOwner) { equivalentSpeedView.text = getString(R.string.result_final_speed, it)}
        viewModel.timePerCharge.observe(viewLifecycleOwner) { timePerChargeView.text = getString(R.string.result_time_per_charge, it)}
        viewModel.totalTimePerCharge.observe(viewLifecycleOwner) { totalTimePerChargeView.text = getString(R.string.result_total_time_per_charge, it)}
        viewModel.distanceFirstCharger.observe(viewLifecycleOwner) { distanceFirstCharger.text = getString(R.string.result_distance_charge_1, it)}
        viewModel.distanceSecondCharger.observe(viewLifecycleOwner) { distanceSecondCharger.text = getString(R.string.result_distance_charge_2, it)}
        viewModel.distanceThirdCharger.observe(viewLifecycleOwner) { distanceThirdCharger.text = getString(R.string.result_distance_charge_3, it)}

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}