package be.hcpl.android.optitripev.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import be.hcpl.android.optitripev.R
import be.hcpl.android.optitripev.util.Constants
import be.hcpl.android.optitripev.util.formatHours
import be.hcpl.android.optitripev.util.toImperial

class ResultView : Fragment() {

    private lateinit var viewModel: ResultViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycle.addObserver(viewModel)
    }
    /*
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {

            viewModel = ViewModelProvider(this)[ResultViewModel::class.java]

            // get all results here
            val optimalSpeedView = binding.resultBestSpeed
            val totalEnergyView = binding.resultTotalTripEnergy
            val numberOfChargesView = binding.resultNumberOfCharges
            val totalTripTimeView = binding.resultTotalTripTime
            val totalDriveTimeView = binding.resultDrivingTime
            val totalChargeTimeView = binding.resultTotalChargeTime
            val finalSpeedView = binding.resultFinalSpeed
            val equivalenChargeSpeedView = binding.resultChargeEquivSpeed
            val timePerChargeView = binding.resultTimePerCharge
            val totalTimePerChargeView = binding.resultTotalTimePerCharge
            val distanceFirstCharger = binding.resultDistanceCharge1
            val distanceSecondCharger = binding.resultDistanceCharge2
            val distanceThirdCharger = binding.resultDistanceCharge3

            // show all results here
             viewModel.optimalSpeed.observe(viewLifecycleOwner) {
                 optimalSpeedView.text = if (viewModel.useMetric)
                     getString(R.string.result_best_speed, it, Constants.UNIT_KPH)
                 else
                     getString(R.string.result_best_speed, it.toDouble().toImperial().toInt(), Constants.UNIT_MPH)
             }
            viewModel.totalTripEnergy.observe(viewLifecycleOwner) { totalEnergyView.text = getString(R.string.result_total_trip_energy, it) }
            viewModel.numberOfCharges.observe(viewLifecycleOwner) { numberOfChargesView.text = getString(R.string.result_number_of_charges, it)}
            viewModel.totalTripTime.observe(viewLifecycleOwner) { totalTripTimeView.text = getString(R.string.result_total_trip_time, formatHours(it))}
            viewModel.totalChargeTime.observe(viewLifecycleOwner) { totalChargeTimeView.text = getString(R.string.result_total_charge_time, formatHours(it))}
            viewModel.totalDriveTime.observe(viewLifecycleOwner) { totalDriveTimeView.text = getString(R.string.result_total_driving_time, formatHours(it))}
            viewModel.equivalentSpeed.observe(viewLifecycleOwner) {
                finalSpeedView.text = if(viewModel.useMetric)
                    getString(R.string.result_final_speed, it, Constants.UNIT_KPH)
                else
                    getString(R.string.result_final_speed, it.toDouble().toImperial().toInt(), Constants.UNIT_MPH)
            }
            viewModel.equivalentChargeSpeed.observe(viewLifecycleOwner) {
                equivalenChargeSpeedView.text = if( viewModel.useMetric)
                    getString(R.string.result_charge_equiv_speed, it, Constants.UNIT_KPH)
                else
                    getString(R.string.result_charge_equiv_speed, it.toDouble().toImperial().toInt(), Constants.UNIT_MPH)
            }
            viewModel.timePerCharge.observe(viewLifecycleOwner) { timePerChargeView.text = getString(R.string.result_time_per_charge, formatHours(it))}
            viewModel.totalTimePerCharge.observe(viewLifecycleOwner) { totalTimePerChargeView.text = getString(R.string.result_total_time_per_charge, formatHours(it))}
            viewModel.distanceFirstCharger.observe(viewLifecycleOwner) {
                distanceFirstCharger.text = if( viewModel.useMetric )
                    getString(R.string.result_distance_charge_1, it, Constants.UNIT_KM)
                else
                    getString(R.string.result_distance_charge_1, it.toDouble().toImperial().toInt(), Constants.UNIT_MI)
            }
            viewModel.distanceSecondCharger.observe(viewLifecycleOwner) {
                distanceSecondCharger.text = if (viewModel.useMetric)
                    getString(R.string.result_distance_charge_2, it, Constants.UNIT_KM)
                else
                    getString(R.string.result_distance_charge_2, it.toDouble().toImperial().toInt(), Constants.UNIT_MI)
            }
            viewModel.distanceThirdCharger.observe(viewLifecycleOwner) {
                distanceThirdCharger.text = if( viewModel.useMetric )
                    getString(R.string.result_distance_charge_3, it, Constants.UNIT_KM)
                else
                    getString(R.string.result_distance_charge_3, it.toDouble().toImperial().toInt(), Constants.UNIT_MI)
            }

            // check for errors
            viewModel.errorMessage.observe(viewLifecycleOwner){
                if(it.isNotEmpty()) Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }


        return root
    }
     */

}