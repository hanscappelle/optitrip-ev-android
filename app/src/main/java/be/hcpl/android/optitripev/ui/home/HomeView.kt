package be.hcpl.android.optitripev.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

class HomeView : Fragment() {

    private lateinit var viewModel: HomeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycle.addObserver(viewModel)
    }

/*
        // bind view references
        val totalDistance: TextInputLayout = binding.totalDistance
        val chargePower: TextInputLayout = binding.chargePower
        val chargeTarget: TextInputLayout = binding.chargeTarget
        val chargeDelay: TextInputLayout = binding.chargeDelay
        val usableEnergy: TextInputLayout = binding.usableEnergy
        val initialSoc: TextInputLayout = binding.initialSoc
        val distanceFirstCharger: TextInputLayout = binding.firstChargeStation
        val resultView: TextView = binding.resultSpeed
        val resultSlowerView: TextView = binding.resultSpeedSlower
        val resultFasterView: TextView = binding.resultSpeedFaster

        // check for changes
        viewModel.lastTotalDistance.observe(viewLifecycleOwner) {
            if( viewModel.useMetric ) {
                totalDistance.editText?.setText(it.toDouble().formatInt())
                totalDistance.hint = getString(R.string.hint_total_distance)
                totalDistance.suffixText = Constants.UNIT_KM
            } else {
                totalDistance.editText?.setText(it.toDouble().toImperial().formatDouble1()) // use double to prevent rounding errors
                totalDistance.setHint(R.string.hint_total_distance_mi)
                totalDistance.suffixText = Constants.UNIT_MI
            }
        }
        viewModel.lastDistanceFirstCharger.observe(viewLifecycleOwner) {
            if( viewModel.useMetric ) {
                distanceFirstCharger.editText?.setText(it.toDouble().formatInt())
                distanceFirstCharger.hint = getString(R.string.hint_first_charge_station)
                distanceFirstCharger.suffixText = Constants.UNIT_KM
            } else {
                distanceFirstCharger.editText?.setText(it.toDouble().toImperial().formatDouble1())  // use double to prevent rounding errors
                distanceFirstCharger.hint = getString(R.string.hint_first_charge_station_mi)
                distanceFirstCharger.suffixText = Constants.UNIT_MI
            }
        }
        viewModel.lastChargePower.observe(viewLifecycleOwner) { chargePower.editText?.setText(it) }
        viewModel.lastChargeTarget.observe(viewLifecycleOwner) { chargeTarget.editText?.setText(it) }
        viewModel.lastChargeDelay.observe(viewLifecycleOwner) { chargeDelay.editText?.setText(it) }
        viewModel.lastUsableEnergy.observe(viewLifecycleOwner) { usableEnergy.editText?.setText(it) }
        viewModel.lastInitialSoc.observe(viewLifecycleOwner) { initialSoc.editText?.setText(it) }

        viewModel.result.observe(viewLifecycleOwner) { resultView.text = it }
        viewModel.resultSlower.observe(viewLifecycleOwner) { resultSlowerView.text = it }
        viewModel.resultFaster.observe(viewLifecycleOwner) { resultFasterView.text = it }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            // show some error here for now
            if(it.isNotEmpty()) Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }

        // Get input text
        totalDistance.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.totalDistance.value = if (viewModel.useMetric) {
                text.toString()
            } else {
                try {
                    text.toString().toDouble().toMetric().toString()
                } catch (e: Exception) {
                    "0"
                }
            }
            viewModel.calculate()
        }
        distanceFirstCharger.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.distanceFirstCharger.value = if (viewModel.useMetric) {
                text.toString()
            } else {
                try {
                    text.toString().toDouble().toMetric().toString()
                } catch (e: Exception) {
                    "0"
                }
            }
            viewModel.calculate()
        }
        chargePower.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.chargePower.value = text.toString()
            viewModel.calculate()
        }
        chargeTarget.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.chargeTarget.value = text.toString()
            viewModel.calculate()
        }
        chargeDelay.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.chargeDelay.value = text.toString()
            viewModel.calculate()
        }
        usableEnergy.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.usableEnergy.value = text.toString()
            viewModel.calculate()
        }
        initialSoc.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.initialSoc.value = text.toString()
            viewModel.calculate()
        }
*/

}