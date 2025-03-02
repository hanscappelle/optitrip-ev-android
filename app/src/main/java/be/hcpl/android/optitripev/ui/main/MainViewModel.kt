package be.hcpl.android.optitripev.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.hcpl.android.optitripev.R
import be.hcpl.android.optitripev.domain.Constants
import be.hcpl.android.optitripev.domain.LocalStorage
import be.hcpl.android.optitripev.domain.LocalStorage.Companion.defaultConfigValues
import be.hcpl.android.optitripev.model.Config
import be.hcpl.android.optitripev.model.ConfigUnit
import be.hcpl.android.optitripev.model.ConfigValue
import be.hcpl.android.optitripev.model.OptiTripInput
import be.hcpl.android.optitripev.model.OptiTripResult
import be.hcpl.android.optitripev.ui.navigation.NavigationItem
import be.hcpl.android.optitripev.ui.navigation.Screen
import kotlin.math.abs
import kotlin.math.ceil

class MainViewModel(
    private val storage: LocalStorage,
) : ViewModel() {

    val config: MutableLiveData<Config> = MutableLiveData()
    val input: MutableLiveData<OptiTripInput> = MutableLiveData()
    val optimalResult: MutableLiveData<OptiTripResult> = MutableLiveData()

    // default app navigation configuration
    val navigationItems = listOf(
        NavigationItem(R.drawable.ic_input, R.string.title_home, Screen.Home),
        NavigationItem(R.drawable.ic_result, R.string.title_dashboard, Screen.Result),
        NavigationItem(R.drawable.ic_settings, R.string.title_notifications, Screen.Config),
        NavigationItem(R.drawable.ic_about, R.string.title_about, Screen.About),
    )

    // default values configuration
    val defaultConfig = Config(
        unit = ConfigUnit.Metric,
        values = defaultConfigValues.map { (key, value) -> ConfigValue(key, value.toFloat()) }
    )

    init {
        // get unit system from storage
        val unitFromStorage = if (storage.getBoolean(Constants.PREF_USE_METRIC)) ConfigUnit.Metric else ConfigUnit.Imperial
        // get current configuration values from storage
        val storedConfig = storage.getCurrentConfig()
        config.value = Config(
            unit = unitFromStorage,
            values = if (storedConfig.isNotEmpty()) storedConfig else defaultConfig.values,
        )
        // last input
        input.value = storage.lastInput()
        // calculate optimal result
        calculateOptimalResult()?.let { optimalResult.value = it }
    }

    fun onUnitChanged(checked: Boolean) {
        config.value?.let {
            config.value = it.copy(unit = if (checked) ConfigUnit.Imperial else ConfigUnit.Metric)
        }
        storage.store(Constants.PREF_USE_METRIC, !checked)
    }

    fun onValueChanged(key: String, value: String) {
        config.value?.let {
            config.value = it.copy(
                values = it.values.map { if (it.atSpeed.toString() == key) it.copy(consumption = value.toFloat()) else it }
            )
        }
        calculateOptimalResult()?.let { optimalResult.value = it }
    }

    fun updateValuesInStorage() {
        config.value?.values?.let { storage.storeConfig(it) }
    }

    fun resetValues() {
        config.value = defaultConfig
        calculateOptimalResult()?.let { optimalResult.value = it }
    }

    fun calculateOptimalResult(): OptiTripResult? {
        return input.value?.let { i ->
            // calculate total trip time for all speeds
            config.value?.values?.map { v ->
                // driving time = total distance / speed
                val drivingTime = i.totalDistance / v.atSpeed
                // total energy = speedByConsumption * total trip distance
                val totalEnergy = v.consumption * i.totalDistance
                // required extra energy = abs ( total energy - ( usable energy * initialSoc ) // also takes initial soc into account
                val extraEnergy = abs(totalEnergy - i.usableEnergy * (i.initialSoc / 100))
                // total charge time = abs ( required extra energy / charge power )
                val totalChargeTime = abs(extraEnergy / i.chargePower)
                // time per charge = ( chargeTarget / 100 ) * ( usableEnergy / chargePower )
                val timePerCharge = (i.chargeTarget / 100) * (i.usableEnergy / i.chargePower)
                // # charges = ceil ( required extra energy / ( charge power * time per charge ) )
                val numberOfCharges = ceil(extraEnergy / (i.chargePower * timePerCharge))
                OptiTripResult(
                    drivingTime = drivingTime.toDouble(),
                    totalEnergy = totalEnergy.toDouble(),
                    extraEnergy = extraEnergy.toDouble(),
                    totalChargeTime = totalChargeTime.toDouble(),
                    timePerCharge = timePerCharge.toDouble(),
                    numberOfCharges = numberOfCharges.toInt(),
                    speed = v.atSpeed.toDouble(),
                )
            }?.sortedBy {
                // formula:
                // driving time + total charge time + ( # charges * charge delay )
                it.totalTime(i.chargeDelay)
            }?.firstOrNull()
            //.associate { it.speed }.toMap()

            // TODO lost next and previous values
            // also display 1 value lower and higher
            //totalTimeBySpeed[optimalSpeedInt - 5]?.let{
            //    resultSlower.value = formatResult(R.string.result_optimal_speed_alternative, optimalSpeedInt-5, it.toString().toFloat())
            //}
            //totalTimeBySpeed[optimalSpeedInt + 5]?.let{
            //    resultFaster.value = formatResult(R.string.result_optimal_speed_alternative, optimalSpeedInt+5, it.toString().toFloat())
            //}
        }
    }

    fun updateInput(newValue: OptiTripInput) {
        input.value?.let {
            input.value = newValue
        }
        storage.storeInput(newValue)
        calculateOptimalResult()?.let { optimalResult.value = it }
    }
}