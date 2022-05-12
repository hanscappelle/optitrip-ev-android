package be.hcpl.android.optitripev.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import be.hcpl.android.optitripev.databinding.FragmentHomeBinding
import com.google.android.material.textfield.TextInputEditText

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // bind view references
        val totalDistance: TextInputEditText = binding.inputTotalDistance
        val chargePower: TextInputEditText = binding.inputChargePower
        val chargeTarget: TextInputEditText = binding.inputChargeTarget
        val chargeDelay: TextInputEditText = binding.inputChargeDelay
        val usableEnergy: TextInputEditText = binding.inputUsableEnergy
        val initialSoc: TextInputEditText = binding.inputInitialSoc
        val distanceFirstCharger: TextInputEditText = binding.inputFirstChargeStation
        val calculate: Button = binding.calculate
        val resultView: TextView = binding.resultSpeed

        // check for changes
        viewModel.result.observe(viewLifecycleOwner) { resultView.text = it }

        // Get input text
        totalDistance.doOnTextChanged { text, _, _, _ -> viewModel.totalDistance.value = text.toString() }
        chargePower.doOnTextChanged { text, _, _, _ -> viewModel.chargePower.value = text.toString() }
        chargeTarget.doOnTextChanged { text, _, _, _ -> viewModel.chargeTarget.value = text.toString() }
        chargeDelay.doOnTextChanged { text, _, _, _ -> viewModel.chargeDelay.value = text.toString() }
        usableEnergy.doOnTextChanged { text, _, _, _ -> viewModel.usableEnergy.value = text.toString() }
        initialSoc.doOnTextChanged { text, _, _, _ -> viewModel.initialSoc.value = text.toString() }
        distanceFirstCharger.doOnTextChanged { text, _, _, _ -> viewModel.distanceFirstCharger.value = text.toString() }

        // performs calculation
        calculate.setOnClickListener { viewModel.calculate() }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}