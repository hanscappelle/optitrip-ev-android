package be.hcpl.android.optitripev.ui.config

import android.os.Bundle
import android.text.InputType
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import be.hcpl.android.optitripev.databinding.FragmentConfigBinding

class ConfigFragment : Fragment() {

    private var _binding: FragmentConfigBinding? = null
    private lateinit var viewModel: ConfigViewModel

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycle.addObserver(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this)[ConfigViewModel::class.java]

        _binding = FragmentConfigBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val configContainerView = binding.configContainer

        // create a screen here with multiple input options + recover option
        // TODO later on also profiles to select from and/or load/export option
        viewModel.speedValues.observe(viewLifecycleOwner){
            // clear container first
            configContainerView.removeAllViews()
            configContainerView.addView(createRow("Speed (km/h)","Wh/km", "kWh/km" ))
            // then reconstruct
            it.forEachIndexed { index, key ->
                val value = viewModel.consumptionValues.value?.get(index)
                configContainerView.addView(createEditableRow(key, (value?.times(1000)).toString() ))
            }
        }
        val updateView = binding.update
        viewModel.updateEnabled.observe(viewLifecycleOwner) { updateView.isEnabled = it }

        // add update button to get all values and store as json in preferences
        updateView.setOnClickListener {
            viewModel.storeNewValues()
        }
        // add recover option to get default values back
        binding.recover.setOnClickListener {
            viewModel.recoverDefaults()
        }
        val useMetricView = binding.useMetric
        val useImperialView = binding.useImperial
        useMetricView.setOnCheckedChangeListener{ _, checked -> viewModel.useMetricSystem(checked)}
        useImperialView.setOnCheckedChangeListener{ _, checked -> viewModel.useMetricSystem(!checked)}
        viewModel.useMetricSystem.observe(viewLifecycleOwner){
            useMetricView.isChecked = it
            useImperialView.isChecked = !it
        }

        return root
    }

    private fun createEditableRow(speed: Int, input: String) : ViewGroup {
        val viewGroup = LinearLayout(context)
        viewGroup.orientation = LinearLayout.HORIZONTAL
        val tv1 = TextView(context)
        val tv3 = TextView(context)
        val edit2 = EditText(context)
        tv1.text = speed.toString()
        tv1.layoutParams = params
        tv1.gravity = Gravity.CENTER
        viewGroup.addView(tv1)
        edit2.setText(input)
        edit2.layoutParams = params
        edit2.gravity = Gravity.END
        edit2.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        edit2.tag = speed // set speed value as tag to be used on storing these values TODO improve
        // on change this value also update value of next view
        edit2.doOnTextChanged{ text, _, _, _ ->
            try {
                val calculatedValue = (text.toString().toDouble() / 1000)
                tv3.text = calculatedValue.toString()
                viewModel.updateValue(speed, calculatedValue)
            } catch (e: Exception) { /* ignore here */ }
        }
        viewGroup.addView(edit2)
        tv3.text = (input.toDouble() / 1000).toString()
        tv3.layoutParams = params
        tv3.gravity = Gravity.CENTER
        viewGroup.addView(tv3)
        return viewGroup
    }

    private fun createRow(label1: String, label2: String, label3: String) : ViewGroup {
        val viewGroup = LinearLayout(context)
        viewGroup.orientation = LinearLayout.HORIZONTAL
        val tv1 = TextView(context)
        tv1.text = label1
        tv1.layoutParams = params
        //tv1.gravity = Gravity.CENTER
        viewGroup.addView(tv1)
        val tv2 = TextView(context)
        tv2.text = label2
        tv2.layoutParams = params
        tv2.gravity = Gravity.CENTER
        viewGroup.addView(tv2)
        val tv3 = TextView(context)
        tv3.text = label3
        tv3.layoutParams = params
        tv3.gravity = Gravity.CENTER
        viewGroup.addView(tv3)
        return viewGroup
    }

    private val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}