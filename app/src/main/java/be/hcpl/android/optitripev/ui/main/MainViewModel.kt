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
    }

    fun updateValuesInStorage() {
        config.value?.values?.let { storage.storeConfig(it) }
    }

    fun resetValues() {
        config.value = defaultConfig
    }

    fun calculateOptimalResult(): OptiTripResult? {
        /*
                    // calculate total trip time for all speeds
                    val totalTimeBySpeed = speedByConsumption.mapValues {
                        // using:
                        // driving time = total distance / speed
                        val drivingTime = totalDistanceValue / it.key
                        // total energy = speedByConsumption * total trip distance
                        val totalEnergy = it.value * totalDistanceValue
                        // required extra energy = abs ( total energy - ( usable energy * initialSoc ) // also takes initial soc into account
                        val extraEnergy = abs(totalEnergy - usableEnergyValue * (initialSocValue / 100))
                        // total charge time = abs ( required extra energy / charge power )
                        val totalChargeTime = abs(extraEnergy / chargePowerValue)
                        // time per charge = ( chargeTarget / 100 ) * ( usableEnergy / chargePower )
                        val timePerCharge = (chargeTargetValue / 100) * (usableEnergyValue / chargePowerValue)
                        // # charges = ceil ( required extra energy / ( charge power * time per charge ) )
                        val numberOfCharges = ceil(extraEnergy / (chargePowerValue * timePerCharge))

                        val result = OptiTripResult(
                            drivingTime = drivingTime,
                            totalEnergy = totalEnergy,
                            extraEnergy = extraEnergy,
                            totalChargeTime = totalChargeTime,
                            timePerCharge = timePerCharge,
                            numberOfCharges = numberOfCharges.toInt(),
                            speed = it.key.toDouble(),
                            speedEquiv = it.key.toDouble(),
                        )
                        resultsBySpeed[it.key] = result

                        // formula:
                        // driving time + total charge time + ( # charges * charge delay )
                        drivingTime + totalChargeTime + (numberOfCharges * chargeDelayValue)
                    }

                    // and from that new collection get the lowest value to display
                    val optimalSpeedMap = totalTimeBySpeed.minByOrNull { it.value } ?: 0.0
                    val optimalSpeed = (optimalSpeedMap as Map.Entry<*, *>).key
                    val totalTime = optimalSpeedMap.value
                    val totalTimeValue = totalTime.toString().toFloat()
                    val optimalSpeedInt = Integer.parseInt(optimalSpeed.toString())
                    result.value = formatResult(R.string.result_optimal_speed, optimalSpeedInt, totalTimeValue)
                    // also display 1 value lower and higher
                    totalTimeBySpeed[optimalSpeedInt - 5]?.let{
                        resultSlower.value = formatResult(R.string.result_optimal_speed_alternative, optimalSpeedInt-5, it.toString().toFloat())
                    }
                    totalTimeBySpeed[optimalSpeedInt + 5]?.let{
                        resultFaster.value = formatResult(R.string.result_optimal_speed_alternative, optimalSpeedInt+5, it.toString().toFloat())
                    }
        */
        // FIXME calculation needed here
        return null

    }

    fun updateInput(newValue: OptiTripInput) {
        input.value?.let {
            input.value = newValue
        }
        storage.storeInput(newValue)
    }


}