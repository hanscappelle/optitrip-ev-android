package be.hcpl.android.optitripev.ui.home

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import be.hcpl.android.optitripev.R
import be.hcpl.android.optitripev.model.OptiTripResult
import be.hcpl.android.optitripev.util.Constants
import be.hcpl.android.optitripev.util.formatInt
import be.hcpl.android.optitripev.util.toImperial
import java.lang.Exception
import kotlin.math.abs
import kotlin.math.ceil

class HomeViewModel(application: Application) : AndroidViewModel(application),
    DefaultLifecycleObserver {

    private val context by lazy { getApplication<Application>().applicationContext }
    private val prefs = context.getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)
    var useMetric = prefs.getBoolean(Constants.PREF_USE_METRIC, true)

    val chargeTarget = MutableLiveData<String>()
    val chargeDelay = MutableLiveData<String>()
    val usableEnergy = MutableLiveData<String>()
    val distanceFirstCharger = MutableLiveData<String>()
    val initialSoc = MutableLiveData<String>()
    val totalDistance = MutableLiveData<String>()
    val chargePower = MutableLiveData<String>()

    val lastChargeTarget = MutableLiveData<String>()
    val lastChargeDelay = MutableLiveData<String>()
    val lastUsableEnergy = MutableLiveData<String>()
    val lastDistanceFirstCharger = MutableLiveData<String>()
    val lastInitialSoc = MutableLiveData<String>()
    val lastTotalDistance = MutableLiveData<String>()
    val lastChargePower = MutableLiveData<String>()

    val result = MutableLiveData<String>()
    private val resultsBySpeed = emptyMap<Int, OptiTripResult>().toMutableMap()
    val errorMessage = MutableLiveData<String>()
    private var speedByConsumption = Constants.getValidConfig(prefs)

    fun calculate() {
        useMetric = prefs.getBoolean(Constants.PREF_USE_METRIC, true)
        // TODO parse values from text, show errors where needed
        try {
            // get user entered values with fallback to some defaults here
            val totalDistanceValue = totalDistance.value?.toDouble() ?: 1000.0
            val usableEnergyValue = usableEnergy.value?.toDouble() ?: 13.0
            val initialSocValue = initialSoc.value?.toDouble() ?: 100.0
            val chargePowerValue = chargePower.value?.toDouble() ?: 13.0
            val chargeTargetValue = chargeTarget.value?.toDouble() ?: 100.0
            // entered charge delay time is in minutes, calculate that to hours
            val chargeDelayValue =
                (chargeDelay.value?.toDouble() ?: 0.0) * Constants.MINUTES_TO_HOUR

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
            )
            resultsBySpeed[it.key] = result

            // formula:
            // driving time + total charge time + ( # charges * charge delay )
            drivingTime + totalChargeTime + (numberOfCharges * chargeDelayValue)
        }

        // and from that new collection get the lowest value to display
        val optimalSpeedMap = totalTimeBySpeed.minByOrNull { it.value } ?: 0.0
        val optimalSpeed = (optimalSpeedMap as Map.Entry<*, *>).key
        val optimalSpeedInt = Integer.parseInt(optimalSpeed.toString())
        result.value = if( useMetric ) {
            String.format(context.getString(R.string.result_optimal_speed), optimalSpeedInt, Constants.UNIT_KPH)
        } else {
            String.format(context.getString(R.string.result_optimal_speed),
                optimalSpeedInt.toDouble().toImperial().toInt(), Constants.UNIT_MI)
        }

        // store for use elsewhere
        prefs.edit()
            .putInt(Constants.RESULT_OPTIMAL_SPEED, optimalSpeedInt)
            .putFloat(Constants.RESULT_TOTAL_ENERGY, resultsBySpeed[optimalSpeedInt]?.totalEnergy?.toFloat()?: 0f)
            .putInt(Constants.RESULT_NUMBER_OF_CHARGES, resultsBySpeed[optimalSpeedInt]?.numberOfCharges?: 0)
            .putFloat(Constants.RESULT_TOTAL_DRIVE_TIME, resultsBySpeed[optimalSpeedInt]?.drivingTime?.toFloat()?: 0f)
            .putFloat(Constants.RESULT_TOTAL_CHARGE_TIME, resultsBySpeed[optimalSpeedInt]?.totalChargeTime?.toFloat()?: 0f)
            .putFloat(Constants.RESULT_CALCULATED_EFFICIENCY, speedByConsumption[optimalSpeedInt]?.toFloat()?: 0f)
            .apply()

            errorMessage.value = "" // clear errors

        } catch (e: Exception) {
            errorMessage.value = context.getString(R.string.err_calculating_result)
        }
    }

    // region store user input values in preferences and recover on resume

    override fun onResume(owner: LifecycleOwner) {
        speedByConsumption = Constants.getValidConfig(prefs)
        lastChargeTarget.value = prefs.getString(Constants.PREF_KEY_CHARGE_TARGET, "90")
        lastChargeDelay.value = prefs.getString(Constants.PREF_KEY_CHARGE_DELAY, "2")
        lastChargePower.value = prefs.getString(Constants.PREF_KEY_CHARGE_POWER, "11.7")
        lastUsableEnergy.value = prefs.getString(Constants.PREF_KEY_USABLE_ENERGY, "12.6")
        lastInitialSoc.value = prefs.getString(Constants.PREF_KEY_INITIAL_SOC, "100")
        lastTotalDistance.value = prefs.getString(Constants.PREF_KEY_TOTAL_DISTANCE, "1000")
        lastDistanceFirstCharger.value = prefs.getString(Constants.PREF_KEY_DISTANCE_FIRST_CHARGER, "100")
        calculate() // calculate directly on resume also
    }

    override fun onPause(owner: LifecycleOwner) {
        prefs.edit()
            .putString(Constants.PREF_KEY_CHARGE_TARGET, chargeTarget.value)
            .putString(Constants.PREF_KEY_CHARGE_DELAY, chargeDelay.value)
            .putString(Constants.PREF_KEY_CHARGE_POWER, chargePower.value)
            .putString(Constants.PREF_KEY_USABLE_ENERGY, usableEnergy.value)
            .putString(Constants.PREF_KEY_INITIAL_SOC, initialSoc.value)
            .putString(Constants.PREF_KEY_TOTAL_DISTANCE, totalDistance.value)
            .putString(Constants.PREF_KEY_DISTANCE_FIRST_CHARGER, distanceFirstCharger.value)
            .apply()
    }

    // endregion

}