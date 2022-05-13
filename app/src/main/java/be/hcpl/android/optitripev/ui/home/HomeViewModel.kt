package be.hcpl.android.optitripev.ui.home

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.provider.Settings.Global.getString
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.hcpl.android.optitripev.R
import be.hcpl.android.optitripev.util.Constants
import kotlin.math.abs
import kotlin.math.ceil

class HomeViewModel(application: Application) : AndroidViewModel(application),
    DefaultLifecycleObserver {

    private val context by lazy { getApplication<Application>().applicationContext }
    private val prefs = context.getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)

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

    // TODO parse values from text, show errors where needed

    fun calculate() {
        // get user entered values with fallback to some defaults here
        val totalDistanceValue = totalDistance.value?.toDouble() ?: 1000.0
        val usableEnergyValue = usableEnergy.value?.toDouble() ?: 13.0
        val initialSocValue = initialSoc.value?.toDouble() ?: 100.0
        val chargePowerValue = chargePower.value?.toDouble() ?: 13.0
        val chargeTargetValue = chargeTarget.value?.toDouble() ?: 100.0
        // entered charge delay time is in minutes, calculate that to hours
        val chargeDelayValue = (chargeDelay.value?.toDouble() ?: 0.0) * 0.0166667

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

            // formula:
            // driving time + total charge time + ( # charges * charge delay )
            drivingTime + totalChargeTime + (numberOfCharges * chargeDelayValue)
        }

        // and from that new collection get the lowest value to display
        val optimalSpeedMap = totalTimeBySpeed.minByOrNull { it.value } ?: 0.0
        val optimalSpeed = (optimalSpeedMap as Map.Entry<*, *>).key
        result.value = String.format(
            context.getString(R.string.result_optimal_speed),
            optimalSpeed
        )

        // store for use elsewhere
        prefs.edit()
            .putInt(Constants.RESULT_OPTIMAL_SPEED, Integer.parseInt(optimalSpeed.toString()))
            .apply()
    }

    // region store user input values in preferences and recover on resume

    override fun onResume(owner: LifecycleOwner) {
        lastChargeTarget.value = prefs.getString(Constants.PREF_KEY_CHARGE_TARGET, "")
        lastChargeDelay.value = prefs.getString(Constants.PREF_KEY_CHARGE_DELAY, "")
        lastChargePower.value = prefs.getString(Constants.PREF_KEY_CHARGE_POWER, "")
        lastUsableEnergy.value = prefs.getString(Constants.PREF_KEY_USABLE_ENERGY, "")
        lastInitialSoc.value = prefs.getString(Constants.PREF_KEY_INITIAL_SOC, "")
        lastTotalDistance.value = prefs.getString(Constants.PREF_KEY_TOTAL_DISTANCE, "")
        lastDistanceFirstCharger.value = prefs.getString(Constants.PREF_KEY_DISTANCE_FIRST_CHARGER, "")
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

    // TODO move this table to a configurable view so users can have their own
    val speedByConsumption = mapOf(
        30 to 0.027,
        35 to 0.029,
        40 to 0.032,
        45 to 0.034,
        50 to 0.037,
        55 to 0.041,
        60 to 0.045,
        65 to 0.049,
        70 to 0.053,
        75 to 0.058,
        80 to 0.063,
        85 to 0.068,
        90 to 0.075,
        95 to 0.081,
        100 to 0.089,
        105 to 0.097,
        110 to 0.105,
        115 to 0.115,
        120 to 0.125,
        125 to 0.136,
        130 to 0.148,
        135 to 0.162,
        140 to 0.176,
    )
}