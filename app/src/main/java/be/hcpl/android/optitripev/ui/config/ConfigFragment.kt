package be.hcpl.android.optitripev.ui.config

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import be.hcpl.android.optitripev.databinding.FragmentConfigBinding
import be.hcpl.android.optitripev.ui.result.ResultViewModel

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

        viewModel = ViewModelProvider(this).get(ConfigViewModel::class.java)

        _binding = FragmentConfigBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val configContainerView = binding.configContainer

        // TODO create a screen here with multiple input options + recover option + later on also profiles
        configContainerView.addView(createRow("Speed","Wh/km", "kWh/km" ))
        viewModel.speedValues.observe(viewLifecycleOwner){
            it.forEachIndexed { index, key ->
                val value = viewModel.consumptionValues.value?.get(index)
                configContainerView.addView(createEditableRow(key.toString(), (value?.times(1000)).toString(), value.toString() ))
            }
        }

        // TODO add update button to get all values and store as json in preferences
        // TODO add recover option to get default values back

        // TODO later on get profiles that users can select from

        return root
    }

    private fun createEditableRow(label1: String, input1: String, label2: String) : ViewGroup {
        val viewGroup = LinearLayout(context)
        viewGroup.orientation = LinearLayout.HORIZONTAL
        val tv1 = TextView(context)
        tv1.text = label1
        tv1.layoutParams = params
        viewGroup.addView(tv1)
        val edit2 = EditText(context)
        edit2.setText(input1)
        edit2.layoutParams = params
        viewGroup.addView(edit2)
        val tv3 = TextView(context)
        tv3.text = label2
        tv3.layoutParams = params
        viewGroup.addView(tv3)
        return viewGroup

    }

    private fun createRow(label1: String, label2: String, label3: String) : ViewGroup {
        val viewGroup = LinearLayout(context)
        viewGroup.orientation = LinearLayout.HORIZONTAL
        val tv1 = TextView(context)
        tv1.text = label1
        tv1.layoutParams = params
        viewGroup.addView(tv1)
        val tv2 = TextView(context)
        tv2.text = label2
        tv2.layoutParams = params
        viewGroup.addView(tv2)
        val tv3 = TextView(context)
        tv3.text = label3
        tv3.layoutParams = params
        viewGroup.addView(tv3)
        return viewGroup
    }

    private val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}