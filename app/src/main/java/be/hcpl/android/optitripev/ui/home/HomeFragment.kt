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
import be.hcpl.android.optitripev.databinding.FragmentHomeBinding
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
        val totalDistance: TextInputEditText = binding.inputTotalDistance
        val chargePower: TextInputEditText = binding.inputChargePower
        val chargeTarget: TextInputLayout = binding.chargeTarget
        val chargeDelay: TextInputEditText = binding.inputChargeDelay
        val usableEnergy: TextInputEditText = binding.inputUsableEnergy
        val initialSoc: TextInputEditText = binding.inputInitialSoc
        val distanceFirstCharger: TextInputEditText = binding.inputFirstChargeStation
        val resultView: TextView = binding.resultSpeed

        // check for changes
        viewModel.lastTotalDistance.observe(viewLifecycleOwner) {
            //if( viewModel.)
            totalDistance.setText(it)
        }
        viewModel.lastChargePower.observe(viewLifecycleOwner) { chargePower.setText(it) }
        viewModel.lastChargeTarget.observe(viewLifecycleOwner) {
            chargeTarget.editText?.setText(it) // TODO should we target TextInputLayout like this instead?
        }
        viewModel.lastChargeDelay.observe(viewLifecycleOwner) { chargeDelay.setText(it) }
        viewModel.lastUsableEnergy.observe(viewLifecycleOwner) { usableEnergy.setText(it) }
        viewModel.lastInitialSoc.observe(viewLifecycleOwner) { initialSoc.setText(it) }
        viewModel.lastDistanceFirstCharger.observe(viewLifecycleOwner) { distanceFirstCharger.setText(it) }

        viewModel.result.observe(viewLifecycleOwner) { resultView.text = it }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            // show some error here for now
            if(it.isNotEmpty()) Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }

        // Get input text
        totalDistance.doOnTextChanged { text, _, _, _ ->
            viewModel.totalDistance.value = text.toString()
            viewModel.calculate()
        }
        chargePower.doOnTextChanged { text, _, _, _ ->
            viewModel.chargePower.value = text.toString()
            viewModel.calculate()
        }
        chargeTarget.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.chargeTarget.value = text.toString()
            viewModel.calculate()
        }
        chargeDelay.doOnTextChanged { text, _, _, _ ->
            viewModel.chargeDelay.value = text.toString()
            viewModel.calculate()
        }
        usableEnergy.doOnTextChanged { text, _, _, _ ->
            viewModel.usableEnergy.value = text.toString()
            viewModel.calculate()
        }
        initialSoc.doOnTextChanged { text, _, _, _ ->
            viewModel.initialSoc.value = text.toString()
            viewModel.calculate()
        }
        distanceFirstCharger.doOnTextChanged { text, _, _, _ ->
            viewModel.distanceFirstCharger.value = text.toString()
            viewModel.calculate()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}