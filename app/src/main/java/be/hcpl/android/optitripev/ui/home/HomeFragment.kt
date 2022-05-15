package be.hcpl.android.optitripev.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import be.hcpl.android.optitripev.R
import be.hcpl.android.optitripev.databinding.FragmentHomeBinding
import be.hcpl.android.optitripev.util.Constants
import be.hcpl.android.optitripev.util.formatInt
import be.hcpl.android.optitripev.util.toImperial
import be.hcpl.android.optitripev.util.toMetric
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: HomeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycle.addObserver(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // bind view references
        val totalDistance: TextInputLayout = binding.totalDistance
        val chargePower: TextInputLayout = binding.chargePower
        val chargeTarget: TextInputLayout = binding.chargeTarget
        val chargeDelay: TextInputLayout = binding.chargeDelay
        val usableEnergy: TextInputLayout = binding.usableEnergy
        val initialSoc: TextInputLayout = binding.initialSoc
        val distanceFirstCharger: TextInputLayout = binding.firstChargeStation
        val resultView: TextView = binding.resultSpeed

        // check for changes
        viewModel.lastTotalDistance.observe(viewLifecycleOwner) {
            if( viewModel.useMetric ) {
                totalDistance.editText?.setText(it.toDouble().formatInt())
                totalDistance.hint = getString(R.string.hint_total_distance)
                totalDistance.suffixText = Constants.UNIT_KM
            } else {
                totalDistance.editText?.setText(it.toDouble().toImperial().formatInt())
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
                distanceFirstCharger.editText?.setText(it.toDouble().toImperial().formatInt())
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

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}